package com.jaime.peligo.network

import com.jaime.peligo.network.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("now_playing")
    suspend fun getAllMovies(
        @Query("api_key") apikey:String,
        @Query("language") language: String = "es-ES"
    ):Response<MoviesResponse>

    @GET("popular")
    suspend fun getPopular(
        @Query("api_key") apikey:String,
        @Query("language") language: String = "es-ES"
    ):Response<MoviesResponse>

    @GET("top_rated")
    suspend fun getTopRated(
        @Query("api_key") apikey:String,
        @Query("language") language: String = "es-ES"
    ):Response<MoviesResponse>

    @GET("upcoming")
    suspend fun getUpcoming(
        @Query("api_key") apikey:String,
        @Query("language") language: String = "es-ES"
    ):Response<MoviesResponse>


}