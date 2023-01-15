package com.elephantapps.marvelapp.ui.CharacterList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elephantapps.marvelapp.domain.use_cases.CharactersUseCase
import com.elephantapps.marvelapp.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersUseCase: CharactersUseCase
): ViewModel() {

    private val _marvelValue = MutableStateFlow(MarvelListState())
    var marvelValue : StateFlow<MarvelListState> = _marvelValue
    var paginatedValue = 20

    fun getAllCharacters(offset: Int) = viewModelScope.launch(Dispatchers.IO) {
        charactersUseCase(offset).collect {
            when(it){
                is Response.Loading -> {
                    _marvelValue.value = MarvelListState(isLoading = true)
                }
                is Response.Success -> {
                    _marvelValue.value = MarvelListState(characterList = it.data ?: emptyList())
                    paginatedValue += 20
                }
                is Response.Error -> {
                    _marvelValue.value = MarvelListState(error = it.message ?: "Unexpected Error Occurs")
                }
            }
        }
    }
}