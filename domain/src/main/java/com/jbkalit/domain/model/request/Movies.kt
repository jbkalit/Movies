package com.jbkalit.domain.model.request

import com.google.gson.annotations.SerializedName
import com.jbkalit.domain.model.Movie

data class Movies(
    @SerializedName("page") var page: Int,
    @SerializedName("total_results") var total_results: Int,
    @SerializedName("total_pages") var total_pages: Int,
    @SerializedName("results") var results: List<Movie>
)
