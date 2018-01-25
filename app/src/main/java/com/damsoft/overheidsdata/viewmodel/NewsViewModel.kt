package com.damsoft.overheidsdata.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.content.Context
import com.damsoft.overheidsdata.api.Resource
import com.damsoft.overheidsdata.db.SourceEntity
import com.damsoft.overheidsdata.db.ThemeEntity
import com.damsoft.overheidsdata.ui.api.APIInterface
import com.damsoft.overheidsdata.ui.api.ThemesAPIInterface
import com.damsoft.overheidsdata.model.packages.DataSets
import com.damsoft.overheidsdata.ui.repo.NewsRepository
import com.damsoft.overheidsdata.ui.repo.ThemesRepository

/**
 * Created by abhinav.sharma on 01/11/17.
 */
class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val newsRepo = NewsRepository(APIInterface.getAPIService())
    private val themesRepo = ThemesRepository(ThemesAPIInterface.getAPIService())

    val context: Context = application.applicationContext

    fun getNewsSource(language: String?, category: String?, country: String?): LiveData<Resource<List<SourceEntity>>> {
        return newsRepo.fetchNewsSource(context,language, category, country)
    }

    fun getDataSets(theme: String, sortBy: String?) : LiveData<DataSets> {
        return newsRepo.DataSets(theme)
    }

    fun getThemes(): LiveData<Resource<List<ThemeEntity>>> {
        return themesRepo.fetchThemes(context)
    }
}