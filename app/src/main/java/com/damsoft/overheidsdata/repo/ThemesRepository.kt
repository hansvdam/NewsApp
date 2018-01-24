package com.damsoft.overheidsdata.ui.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.damsoft.overheidsdata.BuildConfig
import com.damsoft.overheidsdata.RateLimiter
import com.damsoft.overheidsdata.api.ApiResponse
import com.damsoft.overheidsdata.api.NetworkBoundResource
import com.damsoft.overheidsdata.api.Resource
import com.damsoft.overheidsdata.db.NewsDBHelper
import com.damsoft.overheidsdata.db.SourceEntity
import com.damsoft.overheidsdata.db.ThemeEntity
import java.util.concurrent.TimeUnit
import com.damsoft.overheidsdata.ui.api.APIInterface
import com.damsoft.overheidsdata.ui.api.ThemesAPIInterface
import com.damsoft.overheidsdata.ui.model.ArticlesResponse
import com.damsoft.overheidsdata.ui.model.SourceResponse
import com.damsoft.overheidsdata.ui.model.ThemeResponse
import com.damsoft.overheidsdata.ui.model.ThemeResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by abhinav.sharma on 31/10/17.
 */
class ThemesRepository(private val apiInterface: ThemesAPIInterface) {

    val repoRateLimiter = RateLimiter<String>(10, TimeUnit.MINUTES)

    fun fetchThemes(context: Context): LiveData<Resource<List<ThemeEntity>>> {
        return object : NetworkBoundResource<List<ThemeEntity>, ThemeResponse>() {
            override fun onFetchFailed() {
                repoRateLimiter.reset("all")
            }

            override fun saveCallResult(item: ThemeResponse) {
//                To avoid this make API response pojo class as entity
                var sourceList = ArrayList<ThemeEntity>()
                item.sources.forEach {
                    var ThemeEntity = ThemeEntity()
                    ThemeEntity.id = it.id
                    ThemeEntity.description = it.description
                    ThemeEntity.name = it.name
                    ThemeEntity.theme_facet = it.theme_facet
                    sourceList.add(ThemeEntity)
                }
                NewsDBHelper.getInstance(context).getThemeDao().insertSources(sourceList)
            }

            override fun shouldFetch(data: List<ThemeEntity>?): Boolean = repoRateLimiter.shouldFetch("all")

            override fun loadFromDb(): LiveData<List<ThemeEntity>> {
                return NewsDBHelper.getInstance(context).getThemeDao().getAllThemes()
            }

            override fun createCall(): LiveData<ApiResponse<ThemeResponse>> {
                return apiInterface.getThemes()
//                val returnValue = MutableLiveData<ApiResponse<ThemeResponse>>()
//                returnValue.value = ApiResponse(Response.success(ThemeResponse("ok", mutableListOf(ThemeResponseItem("bb","name1","desc1","facet1")))))
//                return returnValue
            }
        }.asLiveData()

    }
}