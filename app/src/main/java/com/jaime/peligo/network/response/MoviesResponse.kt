package com.jaime.peligo.network.response

import com.google.gson.annotations.SerializedName
import com.jaime.peligo.models.MovieModel

data class MoviesResponse(
    @SerializedName("results")
    var results:List<MovieModel>
)
