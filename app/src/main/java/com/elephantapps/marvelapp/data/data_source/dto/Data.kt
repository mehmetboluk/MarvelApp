package com.elephantapps.marvelapp.data.data_source.dto

data class Data(
    val count: String,
    val limit: String,
    val offset: String,
    val results: List<Result>,
    val total: String
)