package com.jbkalit.domain.model

import com.google.gson.annotations.SerializedName

data class Review (
    @SerializedName("id") var id: String? = null,
    @SerializedName("author") var author: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("content") var content: String? = null,
)
