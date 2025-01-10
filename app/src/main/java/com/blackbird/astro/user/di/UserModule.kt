package com.blackbird.astro.user.di

import com.blackbird.astro.user.data.UserEntryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class UserModule {

    @Provides
    @Singleton
    fun provideUserEntryRepository(): UserEntryRepository = UserEntryRepository()


}