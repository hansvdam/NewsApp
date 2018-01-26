package com.damsoft.overheidsdata.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.damsoft.overheidsdata.ui.DBConstants

/**
 * Created by abhinav.sharma on 04/11/17.
 */
@Dao
interface ThemeDao {

    @Query("SELECT * FROM " + DBConstants.TABLE_THEME)
    fun getAllThemes(): LiveData<List<ThemeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertThemes(source: List<ThemeEntity>)

    @Delete
    fun deleteSource(source: List<ThemeEntity>)
}