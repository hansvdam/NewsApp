package com.damsoft.overheidsdata.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.damsoft.overheidsdata.ui.NewsConstants

/**
 * Created by abhinav.sharma on 04/11/17.
 */
@Dao
interface SourceDao {

    @Query("SELECT * FROM " + NewsConstants.T_SOURCE)
    fun getAllNewsSource(): LiveData<List<SourceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSources(source: List<SourceEntity>)

    @Delete
    fun deleteSource(source: List<SourceEntity>)

//    fun insertThemes(source: List<Source>) {
//
//        insertThemes(*sourceEntityArray.toTypedArray())
//    }
}