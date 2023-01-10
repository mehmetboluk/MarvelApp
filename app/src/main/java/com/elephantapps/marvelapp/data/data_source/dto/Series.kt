package com.elephantapps.marvelapp.data.data_source.dto

data class Series(
    val available: String,
    val collectionURI: String,
    val items: List<Item>,
    val returned: String
)