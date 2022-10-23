package com.icdominguez.network.api

import com.icdominguez.network.data.model.responses.CharactersResponse
import com.icdominguez.network.data.model.responses.ComicsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("offset") offset: Int,
        @Query("ts") ts: Long,
        @Query("limit") limit: Int = 20,
        @Query("orderBy") orderBy: String = "name"
    ): Response<CharactersResponse>

    @GET("characters/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Long
    ): Response<CharactersResponse>

    @GET("comics")
    suspend fun getComics(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Long,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int,
        @Query("format") format: String = "infinite comic",
        @Query("noVariant") noVariants: Boolean = true,
        @Query("formatType") formatType: String = "comic"
    ): Response<ComicsResponse>
}
