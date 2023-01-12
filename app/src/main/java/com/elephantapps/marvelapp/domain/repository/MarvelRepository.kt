package com.elephantapps.marvelapp.domain.repository

import com.elephantapps.marvelapp.data.data_source.dto.CharactersDTO

interface MarvelRepository {

    suspend fun getAllCharacter(offset: Int): CharactersDTO
}