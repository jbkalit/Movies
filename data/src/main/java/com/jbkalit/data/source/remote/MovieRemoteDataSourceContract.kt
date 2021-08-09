package com.jbkalit.data.source.remote

import com.jbkalit.domain.model.Movie
import com.jbkalit.domain.model.request.Genres
import com.jbkalit.domain.model.request.Movies
import com.jbkalit.domain.model.request.Reviews
import com.jbkalit.domain.model.request.Videos
import io.reactivex.Single

interface MovieRemoteDataSourceContract {

    fun getGenreList(): Single<Genres>

    suspend fun getMoviesByGenre(page: Int, id: Int): Movies

    fun getMovieById(id: Int): Single<Movie>

    fun getReviewByMovieId(id: Int, page: Int): Single<Reviews>

    fun getVideoByMovieId(id: Int): Single<Videos>

}
