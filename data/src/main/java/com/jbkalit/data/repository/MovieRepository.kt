package com.jbkalit.data.repository

import com.jbkalit.data.source.remote.MovieRemoteDataSourceContract
import com.jbkalit.domain.model.Movie
import com.jbkalit.domain.model.request.Genres
import com.jbkalit.domain.model.request.Movies
import com.jbkalit.domain.model.request.Reviews
import com.jbkalit.domain.model.request.Videos
import com.jbkalit.domain.repository.MovieRepositoryContract
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject
constructor(private val movieRemoteDataSource: MovieRemoteDataSourceContract)
    : MovieRepositoryContract {

    override fun getGenreList(): Single<Genres> {
        return movieRemoteDataSource.getGenreList()
    }

    override fun getMoviesByGenre(page: Int, id: Int): Single<Movies> {
        return movieRemoteDataSource.getMoviesByGenre(page, id)
    }

    override fun getMovieById(id: Int): Single<Movie> {
        return movieRemoteDataSource.getMovieById(id)
    }

    override fun getReviewByMovieId(id: Int, page: Int): Single<Reviews> {
        return movieRemoteDataSource.getReviewByMovieId(id, page)
    }

    override fun getVideoByMovieId(id: Int): Single<Videos> {
        return movieRemoteDataSource.getVideoByMovieId(id)
    }

}
