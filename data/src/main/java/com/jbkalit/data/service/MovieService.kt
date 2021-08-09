package com.jbkalit.data.service

import com.jbkalit.domain.model.Movie
import com.jbkalit.domain.model.request.Genres
import com.jbkalit.domain.model.request.Movies
import com.jbkalit.domain.model.request.Reviews
import com.jbkalit.domain.model.request.Videos
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(value = "genre/movie/list")
    fun getGenreList(): Single<Genres>

    @GET("discover/movie")
    fun getMoviesByGenre(@Query("page") page: Int,
                         @Query("with_genres") with_genres: Int): Movies

    @GET("movie/{movie_id}")
    fun getMovieById(@Path("movie_id") id: Int): Single<Movie>

    @GET("movie/{movie_id}/reviews")
    fun getReviewByMovieId(@Path("movie_id") id: Int,
                           @Query("page") page: Int): Single<Reviews>

    @GET("movie/{movie_id}/videos")
    fun getVideoByMovieId(@Path("movie_id") id: Int): Single<Videos>

}
