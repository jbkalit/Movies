package com.jbkalit.movies.util

object Helper {

    fun buildYoutubeURL(key: String): String {
        return "https://www.youtube.com/watch?v=$key"
    }

    fun buildYouTubeThumbnailURL(key: String): String {
        return "https://img.youtube.com/vi/$key/0.jpg"
    }

}
