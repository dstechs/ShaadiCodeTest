package com.dinesh.shaadi.di

import android.content.Context
import com.dinesh.shaadi.data.local.AppDatabase
import com.dinesh.shaadi.data.local.MatchesDao
import com.dinesh.shaadi.data.remote.MatchesRemoteDataSource
import com.dinesh.shaadi.data.remote.MatchesService
import com.dinesh.shaadi.data.repository.MatchesRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): MatchesService =
        retrofit.create(MatchesService::class.java)

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(characterService: MatchesService) =
        MatchesRemoteDataSource(characterService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.characterDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: MatchesRemoteDataSource,
        localDataSource: MatchesDao
    ) =
        MatchesRepository(remoteDataSource, localDataSource)
}