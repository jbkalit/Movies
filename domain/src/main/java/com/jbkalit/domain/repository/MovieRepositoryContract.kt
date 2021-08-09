package com.jbkalit.domain.repository

import com.jbkalit.domain.model.Movie
import com.jbkalit.domain.model.request.Genres
import com.jbkalit.domain.model.request.Movies
import com.jbkalit.domain.model.request.Reviews
import com.jbkalit.domain.model.request.Videos
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface MovieRepositoryContract {

    fun getGenreList(): Single<Genres>

    fun getMoviesByGenre(page: Int, id: Int): Flow<Movies>

    fun getMovieById(id: Int): Single<Movie>

    fun getReviewByMovieId(id: Int, page: Int): Single<Reviews>

    fun getVideoByMovieId(id: Int): Single<Videos>

}
