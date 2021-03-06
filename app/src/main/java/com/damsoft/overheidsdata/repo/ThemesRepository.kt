package com.damsoft.overheidsdata.ui.repo

import android.arch.lifecycle.LiveData
import android.content.Context
import com.damsoft.overheidsdata.RateLimiter
import com.damsoft.overheidsdata.api.ApiResponse
import com.damsoft.overheidsdata.api.NetworkBoundResource
import com.damsoft.overheidsdata.api.Resource
import com.damsoft.overheidsdata.apimodel.ThemeResponse
import com.damsoft.overheidsdata.db.DbHelper
import com.damsoft.overheidsdata.db.ThemeEntity
import com.damsoft.overheidsdata.ui.api.ThemesAPIInterface
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * Created by abhinav.sharma on 31/10/17.
 */
class ThemesRepository(private val apiInterface: ThemesAPIInterface) {

    // declare data stale quickly so we can see what happens more easily (than the normal 10 minutes)
    val repoRateLimiter = RateLimiter<String>(30, TimeUnit.SECONDS)

    fun fetchThemes(context: Context): LiveData<Resource<List<ThemeEntity>>> {
        return object : NetworkBoundResource<List<ThemeEntity>, ThemeResponse>() {
            override fun onFetchFailed() {
                repoRateLimiter.reset("all")
            }

            override fun saveCallResult(item: ThemeResponse) {
//                To avoid this make API response pojo class as entity
                var themeList = ArrayList<ThemeEntity>()
                item.sources.forEach {
                    if (it.identifier != null) {
                        var ThemeEntity = ThemeEntity()
                        ThemeEntity.description = it.description
                        ThemeEntity.name = it.name
                        ThemeEntity.theme_facet = it.identifier!!
                        ThemeEntity.number_of_entries = 0 // TODO: fetch these numbers with their own timeout by querying: https://data.overheid.nl/data/api/3/action/package_search?q=theme_facet:"http://standaarden.overheid.nl/owms/terms/Bestuur"&rows=0
                        themeList.add(ThemeEntity)
                    }
                }
                DbHelper.getInstance(context).getThemeDao().insertThemes(themeList)
            }

            override fun shouldFetch(data: List<ThemeEntity>?): Boolean = repoRateLimiter.shouldFetch("all")

            override fun loadFromDb(): LiveData<List<ThemeEntity>> {
                return DbHelper.getInstance(context).getThemeDao().getAllThemes()
            }

            override fun createCall(): LiveData<ApiResponse<ThemeResponse>> {
                return apiInterface.getThemes()
//                val returnValue = MutableLiveData<ApiResponse<ThemeResponse>>()
//                returnValue.value = ApiResponse(Response.success(ThemeResponse("ok", mutableListOf(ThemeItem("bb","name1","desc1","facet1")))))
//                return returnValue
            }
        }.asLiveData()

    }

}