package com.jbkalit.domain.model.request

import com.google.gson.annotations.SerializedName
import com.jbkalit.domain.model.Review

data class Reviews(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("total_results") var total_results: Int? = null,
    @SerializedName("total_pages") var total_pages: Int? = null,
    @SerializedName("results") var results: List<Review>? = null
)
