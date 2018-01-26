package com.damsoft.overheidsdata.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.damsoft.overheidsdata.ui.NewsConstants

/**
 * Created by abhinav.sharma on 04/11/17.
 */
@Database(entities = arrayOf(ThemeEntity::class), version = 1)
abstract class NewsDBHelper : RoomDatabase() {
    abstract fun getThemeDao(): ThemeDao

    companion object {
        private var db: NewsDBHelper? = null

        fun getInstance(context: Context): NewsDBHelper {
            if (db == null) {
                db = Room.databaseBuilder(context, NewsDBHelper::class.java, NewsConstants.DB_NAME).build()
            }
            return db!!
        }
    }

}