package com.satyajeetmohalkar.todocompose.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.satyajeetmohalkar.todocompose.preferences.PreferenceManager
import com.satyajeetmohalkar.todocompose.preferences.PreferenceManagerImpl
import com.satyajeetmohalkar.todocompose.preferences.todoPreferenceStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
abstract class PreferenceModule {


    @Singleton
    @Binds
    abstract fun bindPreferenceManager(preferenceManagerImpl: PreferenceManagerImpl) : PreferenceManager


    companion object {
        @Singleton
        @Provides
        fun provideDataStorePref(@ApplicationContext context: Context): DataStore<Preferences> {
            return context.todoPreferenceStore
        }
    }

}