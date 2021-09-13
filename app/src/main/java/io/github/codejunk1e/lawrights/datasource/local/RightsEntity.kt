package io.github.codejunk1e.lawrights.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RightsEntity(
    @PrimaryKey val id: Int,
    val title: String,
)