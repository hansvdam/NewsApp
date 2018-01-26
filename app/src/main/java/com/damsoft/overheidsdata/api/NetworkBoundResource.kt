package com.damsoft.overheidsdata.api

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 * <p>
 * You can read more about it in the <a href="https://developer.android.com/arch">Architecture
 * Guide</a>.
 *
 * from: https://github.com/googlesamples/android-architecture-components/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/repository/NetworkBoundResource.java
 */
abstract class NetworkBoundResource<DBEntityType, APIModelReponseType> @MainThread constructor() {
    private val resultLiveData = MediatorLiveData<Resource<DBEntityType>>()

    init {
        val dbSource = loadFromDb()
        resultLiveData.addSource(dbSource) { resultType ->
            resultLiveData.removeSource(dbSource)
            if (shouldFetch(resultType)) {
                fetchFromNetwork(dbSource)
            } else {
                resultLiveData.addSource(dbSource) { rT -> resultLiveData.value = Resource.success(rT) }
            }
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<DBEntityType>) {
        val apiResponse = createCall()
        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
        resultLiveData.addSource(dbSource) { resultType ->
            resultLiveData.value = Resource.loading(resultType)
        }

        resultLiveData.addSource(apiResponse) { response ->
            resultLiveData.removeSource(apiResponse)
            resultLiveData.removeSource(dbSource)

            if (response!!.isSuccessful) {
                processResponse(response).let {
                    Observable.fromCallable { saveCallResult(it!!) }
                            .subscribeOn(Schedulers.io())
                            .subscribe()
                }
                // we specially request a new live data,
                // otherwise we will get immediately last cached value,
                // which may not be updated with latest results received from network.
                resultLiveData.addSource(loadFromDb()) { resultType -> resultLiveData.value = Resource.success(resultType) }

            } else {
                onFetchFailed()
                resultLiveData.addSource(dbSource
                ) { resultType -> resultLiveData.value = response.errorMessage?.let { Resource.error(resultType, it) } }
            }
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData(): LiveData<Resource<DBEntityType>> {
        return resultLiveData
    }

    @WorkerThread
    private fun processResponse(response: ApiResponse<APIModelReponseType>): APIModelReponseType? {
        return response.body
    }

    @WorkerThread
    protected abstract fun saveCallResult(item: APIModelReponseType)

    @MainThread
    protected abstract fun shouldFetch(data: DBEntityType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<DBEntityType>

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<APIModelReponseType>>
}