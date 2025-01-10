package com.blackbird.astro.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.blackbird.astro.core.db.AstroDatabase
import com.blackbird.astro.core.db.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "com.blackbird.astro.user_preferences"
)

@Module
@InstallIn(SingletonComponent::class)
object AstroModule {

    @Provides
    @Singleton
    fun provideUserDataStore(@ApplicationContext applicationContext: Context): DataStore<Preferences> =
        applicationContext.userDataStore


    @Provides
    @Singleton
    fun provideAstroDataBase(@ApplicationContext applicationContext: Context): AstroDatabase =
        Room.databaseBuilder(
            context = applicationContext.applicationContext,
            klass = AstroDatabase::class.java,
            name = "astro_data_base"
        ).build()


    @Provides
    @Singleton
    fun provideTransactionDao(astroDatabase: AstroDatabase): TransactionDao =
        astroDatabase.transactionDao

}
