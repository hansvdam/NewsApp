package com.damsoft.overheidsdata.ui.api

import android.arch.lifecycle.LiveData
import com.damsoft.overheidsdata.api.ApiResponse
import com.damsoft.overheidsdata.api.LiveDataCallAdapterFactory
import com.damsoft.overheidsdata.ui.model.ArticlesResponse
import com.damsoft.overheidsdata.ui.model.SourceResponse
import com.damsoft.overheidsdata.ui.model.ThemeResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by abhinav.sharma on 31/10/17.
 */
interface ThemesAPIInterface {

    companion object {
        val THEMESAPI_URL = "https://hansvdam.github.io/"

        fun getAPIService(): ThemesAPIInterface {
            return getApiInterface(THEMESAPI_URL, ThemesAPIInterface::class.java)
        }

    }

    @GET("overheidsthemes.json")
    fun getThemes(): LiveData<ApiResponse<ThemeResponse>>

}