package com.abhinav.newsapp.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.abhinav.newsapp.ui.NewsConstants
import com.abhinav.newsapp.ui.model.Source

/**
 * Created by abhinav.sharma on 04/11/17.
 */
@Dao
interface ThemeDao {

    @Query("SELECT * FROM " + NewsConstants.T_THEME)
    fun getAllThemes(): LiveData<List<ThemeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSources(source: List<ThemeEntity>)

    @Delete
    fun deleteSource(source: List<ThemeEntity>)

//    fun insertSources(source: List<Source>) {
//
//        insertSources(*sourceEntityArray.toTypedArray())
//    }
}