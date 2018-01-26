package com.damsoft.overheidsdata

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.damsoft.overheidsdata.db.DbHelper
import com.damsoft.overheidsdata.db.ThemeEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Created by abhinav.sharma on 13/11/17.
 */
@RunWith(AndroidJUnit4::class)
class ThemesDaoTest {

    private lateinit var DbHelper: DbHelper

    @Before
    fun initDatabase() {
        DbHelper = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                DbHelper::class.java)
                .allowMainThreadQueries()
                .build()
    }

    @After
    fun closeDatabase() {
        DbHelper.close()
    }

    private fun getNewsSourceDummyList(): List<ThemeEntity> {
        var ThemeEntity = ThemeEntity()
        ThemeEntity.name = "Google News"
//        ThemeEntity.category = "General"
        ThemeEntity.description = "Some dummy description"
        ThemeEntity.id = "googlenews"
//        ThemeEntity.language = "en"
        var newsSourceList = ArrayList<ThemeEntity>()
        newsSourceList.add(ThemeEntity)
        return newsSourceList
    }

    @Test
    fun testInsertAndRetrieve() {
        DbHelper.getThemeDao().insertThemes(getNewsSourceDummyList())
        val allNewsSource = DbHelper.getThemeDao().getAllThemes()
        assert(getValue(allNewsSource).equals(getNewsSourceDummyList()))
    }

    @Test
    fun testDelete() {
        DbHelper.getThemeDao().deleteSource(getNewsSourceDummyList())
        val allNewsSource = DbHelper.getThemeDao().getAllThemes()
        assert(getValue(allNewsSource).isEmpty())
    }

    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T?) {
                data[0] = t
                latch.countDown()
                liveData.removeObserver(this)
            }

        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        return data[0] as T
    }
}