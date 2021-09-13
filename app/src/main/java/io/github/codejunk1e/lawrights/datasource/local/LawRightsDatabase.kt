package io.github.codejunk1e.lawrights.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RightsEntity::class), version = 1)
abstract class LawRightsDatabase : RoomDatabase() {
    abstract fun rightsDao(): RightsDao
}