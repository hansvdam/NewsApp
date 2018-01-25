package com.damsoft.overheidsdata.ui.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.damsoft.overheidsdata.RateLimiter
import com.damsoft.overheidsdata.api.ApiResponse
import com.damsoft.overheidsdata.api.NetworkBoundResource
import com.damsoft.overheidsdata.api.Resource
import com.damsoft.overheidsdata.db.NewsDBHelper
import com.damsoft.overheidsdata.db.SourceEntity
import java.util.concurrent.TimeUnit
import com.damsoft.overheidsdata.ui.api.APIInterface
import com.damsoft.overheidsdata.model.SourceResponse
import com.damsoft.overheidsdata.model.packages.DataSets
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by abhinav.sharma on 31/10/17.
 */
class NewsRepository(private val apiInterface: APIInterface) {

    val repoRateLimiter = RateLimiter<String>(10, TimeUnit.MINUTES)

    fun fetchNewsSource(context: Context, language: String?, category: String?, country: String?): LiveData<Resource<List<SourceEntity>>> {
        return object : NetworkBoundResource<List<SourceEntity>, SourceResponse>() {
            override fun onFetchFailed() {
                repoRateLimiter.reset("all")
            }

            override fun saveCallResult(item: SourceResponse) {
//                To avoid this make API response pojo class as entity
                var sourceList = ArrayList<SourceEntity>()
                item.sources.forEach {
                    var sourceEntity = SourceEntity()
                    sourceEntity.id = it.id
                    sourceEntity.category = it.category
                    sourceEntity.country = it.country
                    sourceEntity.description = it.description
                    sourceEntity.language = it.language
                    sourceEntity.name = it.name
                    sourceEntity.url = it.url
                    sourceList.add(sourceEntity)
                }
                NewsDBHelper.getInstance(context).getSourceDao().insertSources(sourceList)
            }

            override fun shouldFetch(data: List<SourceEntity>?): Boolean = repoRateLimiter.shouldFetch("all")

            override fun loadFromDb(): LiveData<List<SourceEntity>> {
                return NewsDBHelper.getInstance(context).getSourceDao().getAllNewsSource()
            }

            override fun createCall(): LiveData<ApiResponse<SourceResponse>> {
                return apiInterface.getSources(language, category, country)
            }
        }.asLiveData()
    }

    fun DataSets(themeFacet: String): LiveData<DataSets> {
        val liveDataArticlesResponse: MutableLiveData<DataSets> = MutableLiveData()
        val themeQueryParam = "theme_facet:\"" + themeFacet + "\""
        apiInterface.getPackages(themeQueryParam).enqueue(object : Callback<DataSets> {
            override fun onFailure(call: Call<DataSets>?, t: Throwable?) {
                Log.e("Oops", "Network error ${t?.message}")
            }

            override fun onResponse(call: Call<DataSets>, response: Response<DataSets>) {
                liveDataArticlesResponse.value = response.body()
            }
        })
        return liveDataArticlesResponse
    }

}