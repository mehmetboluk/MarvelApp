package com.elephantapps.marvelapp.data.data_source

import com.elephantapps.marvelapp.data.data_source.dto.CharactersDTO
import com.elephantapps.marvelapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("v1/public/characters")
    suspend fun getAllCharacters(
        @Query("apikey") apiKey: String = Constants.API_KEY,
        @Query("ts") timeStamp: String = Constants.timeStamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offSet: String
    ): CharactersDTO

    @GET("v1/public/characters")
    suspend fun getAllSearchedCharacters(
        @Query("apikey") apiKey: String = Constants.API_KEY,
        @Query("ts") timeStamp: String = Constants.timeStamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("nameStartsWith") searchText: String
    ): CharactersDTO
}