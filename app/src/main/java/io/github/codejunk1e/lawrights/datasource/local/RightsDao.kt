package io.github.codejunk1e.lawrights.datasource.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RightsDao {

    @Query("SELECT * FROM rightsentity")
    fun getAllRights() :LiveData<List<RightsEntity>>

    @Query("SELECT * FROM rightsentity")
    fun getAll() :List<RightsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(rights: List<RightsEntity>)

    @Query("DELETE FROM rightsentity")
    fun nukeTable()
}