package com.elephantapps.marvelapp.domain.use_cases

import com.elephantapps.marvelapp.domain.model.Character
import com.elephantapps.marvelapp.domain.repository.MarvelRepository
import com.elephantapps.marvelapp.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchedCharacterUseCase @Inject constructor(
    private val marvelRepository: MarvelRepository
) {

    operator fun invoke(searchText: String): Flow<Response<List<Character>>> = flow{
        try {
            emit(Response.Loading())
            val list = marvelRepository.getAllSearchedCharacter(searchText).data.results.map {
                it.toCharacter()
            }
            emit(Response.Success(list))
        }catch (e: HttpException){
            emit(Response.Error(e.printStackTrace().toString()))
        }catch (e: IOException){
            emit(Response.Error(e.printStackTrace().toString()))
        }
    }
}