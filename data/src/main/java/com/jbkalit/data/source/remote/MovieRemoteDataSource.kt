package com.jbkalit.data.source.remote

import com.jbkalit.data.scheduler.BaseSchedulerProvider
import com.jbkalit.data.service.MovieService
import com.jbkalit.domain.model.Movie
import com.jbkalit.domain.model.request.Genres
import com.jbkalit.domain.model.request.Movies
import com.jbkalit.domain.model.request.Reviews
import com.jbkalit.domain.model.request.Videos
import io.reactivex.Single
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val movieService: MovieService,
                                                private val schedulerProvider: BaseSchedulerProvider,
                                                private val dispatcher: CoroutineDispatcher)
    : MovieRemoteDataSourceContract {

    override fun getGenreList(): Single<Genres> {
        return movieService.getGenreList()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }

    override suspend fun getMoviesByGenre(page: Int, id: Int): Movies {
        return withContext(dispatcher) {
            movieService.getMoviesByGenre(page, id)
        }
    }

    override fun getMovieById(id: Int): Single<Movie> {
        return movieService.getMovieById(id)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }

    override fun getReviewByMovieId(id: Int, page: Int): Single<Reviews> {
        return movieService.getReviewByMovieId(id, page)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }

    override fun getVideoByMovieId(id: Int): Single<Videos> {
        return movieService.getVideoByMovieId(id)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }

}
