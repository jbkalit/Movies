package com.jbkalit.domain.usecase

import com.jbkalit.domain.model.Movie
import com.jbkalit.domain.model.request.Genres
import com.jbkalit.domain.model.request.Movies
import com.jbkalit.domain.model.request.Reviews
import com.jbkalit.domain.model.request.Videos
import io.reactivex.Single

interface MovieUseCaseContract {

    fun getGenreList(): Single<Genres>

    fun getMoviesByGenre(page: Int, id: Int): Single<Movies>

    fun getMovieById(id: Int): Single<Movie>

    fun getReviewByMovieId(id: Int, page: Int): Single<Reviews>

    fun getVideoByMovieId(id: Int): Single<Videos>

}
