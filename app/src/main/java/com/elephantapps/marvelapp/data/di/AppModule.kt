package com.elephantapps.marvelapp.data.di

import com.elephantapps.marvelapp.data.data_source.MarvelApi
import com.elephantapps.marvelapp.data.repository.MarvelRepositoryImpl
import com.elephantapps.marvelapp.domain.repository.MarvelRepository
import com.elephantapps.marvelapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule  {

    @Provides
    @Singleton
    fun provideMarvelApi(): MarvelApi =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelApi::class.java)

    @Provides
    @Singleton
    fun provideMarvelRepository(api: MarvelApi): MarvelRepository =
        MarvelRepositoryImpl(api)
}