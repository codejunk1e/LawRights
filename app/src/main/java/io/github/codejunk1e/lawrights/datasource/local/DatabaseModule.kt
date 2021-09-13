package io.github.codejunk1e.lawrights.datasource.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun providesDao( @ProdDB database: LawRightsDatabase): RightsDao {
        return database.rightsDao()
    }

    @Singleton
    @Provides
    @ProdDB
    fun providesDatabase(
        @ApplicationContext applicationContext : Context
    ): LawRightsDatabase {
        return Room.databaseBuilder(applicationContext,
            LawRightsDatabase::class.java, "law-rights-database"
        ).build()
    }

    @Singleton
    @Provides
    @InMemoryDB
    fun providesInMemoryDatabase(
        @ApplicationContext applicationContext : Context
    ): LawRightsDatabase {
        return Room.inMemoryDatabaseBuilder(applicationContext,
            LawRightsDatabase::class.java).build()
    }
}