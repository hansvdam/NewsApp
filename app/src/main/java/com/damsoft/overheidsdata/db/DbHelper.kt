package com.damsoft.overheidsdata.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.damsoft.overheidsdata.ui.DBConstants

/**
 * Created by abhinav.sharma on 04/11/17.
 */
@Database(entities = arrayOf(ThemeEntity::class), version = 1)
abstract class DbHelper : RoomDatabase() {
    abstract fun getThemeDao(): ThemeDao

    companion object {
        private var db: DbHelper? = null

        fun getInstance(context: Context): DbHelper {
            if (db == null) {
                db = Room.databaseBuilder(context, DbHelper::class.java, DBConstants.DB_NAME).build()
            }
            return db!!
        }
    }

}