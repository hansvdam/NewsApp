package com.damsoft.overheidsdata.ui.api

import com.damsoft.overheidsdata.api.LiveDataCallAdapterFactory
import com.damsoft.overheidsdata.apimodel.DataSets
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


/**
 * Created by abhinav.sharma on 31/10/17.
 */
interface DataSetAPIInterface {

    companion object {
        val API_URL = "https://data.overheid.nl/data/api/3/action/"

        // just an OKHttp-client to I can make api-calls time-out faster, so the unhappy flow can be triggered easily
        // by just switching off wifi (when running in the
        val okHttpClient = OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build()

        fun getAPIService(): DataSetAPIInterface {
            return getApiInterface(API_URL, DataSetAPIInterface::class.java)
        }

    }

    @GET("package_search")
    fun getDataSets(@Query("q") themeParamValue: String): Call<DataSets>
}

fun <T> getApiInterface(s: String, clazz: Class<T>): T {
    return Retrofit.Builder()
            .baseUrl(s)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(DataSetAPIInterface.okHttpClient)
            .build()
            .create(clazz)
}
