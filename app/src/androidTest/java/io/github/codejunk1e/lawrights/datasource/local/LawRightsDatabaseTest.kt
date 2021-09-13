package io.github.codejunk1e.lawrights.datasource.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dagger.hilt.android.testing.HiltAndroidTest
import io.github.codejunk1e.lawrights.datasource.mappers.toRightsEntity
import io.github.codejunk1e.lawrights.datasource.network.responses.Rights
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

@HiltAndroidTest
class LawRightsDatabaseTest{
    lateinit var rightsDao: RightsDao
    lateinit var db: LawRightsDatabase
    lateinit var context : Context

    @Before
    fun init() {
        context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context,
            LawRightsDatabase::class.java).build()
        rightsDao = db.rightsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


    @Test
    fun saveAndRetrieveWorks() {
        val rights = listOf(Rights(10, 0, "TAXATION", "2020-08-03 01:25:38"))
            .toRightsEntity()
        rightsDao.insertAll(rights)
        val result = rightsDao.getAll()
        assertThat(rights.get(0), equalTo(result.get(0)))
    }

    @Test
    fun nukeDatabaseWorks() {
        rightsDao.nukeTable()
        val result = rightsDao.getAll()
        assertThat("Database is Empty", result.isEmpty())
    }
}