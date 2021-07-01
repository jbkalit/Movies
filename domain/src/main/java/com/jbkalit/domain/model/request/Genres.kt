package com.jbkalit.domain.model.request

import com.google.gson.annotations.SerializedName
import com.jbkalit.domain.model.Genre

data class Genres (
    @SerializedName("genres") var genres: List<Genre>? = null
)