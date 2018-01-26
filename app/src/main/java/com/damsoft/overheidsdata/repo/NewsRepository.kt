package com.damsoft.overheidsdata.ui.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.damsoft.overheidsdata.RateLimiter
import com.damsoft.overheidsdata.apimodel.DataSets
import com.damsoft.overheidsdata.ui.api.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit


/**
 * Created by abhinav.sharma on 31/10/17.
 */
class NewsRepository(private val apiInterface: APIInterface) {

    val repoRateLimiter = RateLimiter<String>(10, TimeUnit.MINUTES)

    fun fetchDataSets(themeFacet: String): LiveData<DataSets> {
        val liveDataArticlesResponse: MutableLiveData<DataSets> = MutableLiveData()
        val themeQueryParam = "theme_facet:\"" + themeFacet + "\""
        apiInterface.getDataSets(themeQueryParam).enqueue(object : Callback<DataSets> {
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