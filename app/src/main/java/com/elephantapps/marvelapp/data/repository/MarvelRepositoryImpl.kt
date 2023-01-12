package com.elephantapps.marvelapp.data.repository

import com.elephantapps.marvelapp.data.data_source.MarvelApi
import com.elephantapps.marvelapp.data.data_source.dto.CharactersDTO
import com.elephantapps.marvelapp.domain.repository.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val marvelApi: MarvelApi
): MarvelRepository {
    override suspend fun getAllCharacter(offset: Int): CharactersDTO {
        return marvelApi.getAllCharacters(offSet = offset.toString())
    }
}