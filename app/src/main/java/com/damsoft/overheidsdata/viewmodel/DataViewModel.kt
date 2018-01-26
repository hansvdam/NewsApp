package com.damsoft.overheidsdata.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.content.Context
import com.damsoft.overheidsdata.api.Resource
import com.damsoft.overheidsdata.apimodel.DataSets
import com.damsoft.overheidsdata.db.ThemeEntity
import com.damsoft.overheidsdata.ui.api.APIInterface
import com.damsoft.overheidsdata.ui.api.ThemesAPIInterface
import com.damsoft.overheidsdata.ui.repo.NewsRepository
import com.damsoft.overheidsdata.ui.repo.ThemesRepository

/**
 * Created by abhinav.sharma on 01/11/17.
 */
class DataViewModel(application: Application) : AndroidViewModel(application) {

    private val newsRepo = NewsRepository(APIInterface.getAPIService())
    private val themesRepo = ThemesRepository(ThemesAPIInterface.getAPIService())

    val context: Context = application.applicationContext

    fun getDataSets(theme: String, sortBy: String?): LiveData<DataSets> {
        return newsRepo.fetchDataSets(theme)
    }

    fun getThemes(): LiveData<Resource<List<ThemeEntity>>> {
        return themesRepo.fetchThemes(context)
    }
}