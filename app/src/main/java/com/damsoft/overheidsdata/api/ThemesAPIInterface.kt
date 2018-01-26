package com.damsoft.overheidsdata.ui.api

import android.arch.lifecycle.LiveData
import com.damsoft.overheidsdata.api.ApiResponse
import com.damsoft.overheidsdata.apimodel.ThemeResponse
import retrofit2.http.GET


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