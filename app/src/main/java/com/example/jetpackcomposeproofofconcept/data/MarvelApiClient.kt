package com.example.jetpackcomposeproofofconcept.data

import javax.inject.Inject

class MarvelApiClient @Inject constructor(
    private val marvelApiService: MarvelApiService
) {
    suspend fun getCharacters(apiKey: String, hash: String, offset: Int, ts: Long) =
        marvelApiService.getCharacters(apiKey = apiKey, hash = hash, offset = offset, ts = ts)
}
