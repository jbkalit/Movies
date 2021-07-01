package com.jbkalit.domain.model.request

import com.google.gson.annotations.SerializedName
import com.jbkalit.domain.model.Video

data class Videos(
    @SerializedName("results") var results: List<Video>? = null
)
