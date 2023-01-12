package com.elephantapps.marvelapp.ui.CharacterList

import com.elephantapps.marvelapp.domain.model.Character

data class MarvelListState(
    var isLoading: Boolean = false,
    var characterList: List<Character> = emptyList(),
    var error: String = ""
)
