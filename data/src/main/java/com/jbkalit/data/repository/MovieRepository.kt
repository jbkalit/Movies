package com.jbkalit.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jbkalit.data.service.MovieService
import com.jbkalit.data.source.remote.MovieRemoteDataSourceContract
import com.jbkalit.data.source.paging.MoviesPagingSource
import com.jbkalit.domain.model.Movie
import com.jbkalit.domain.model.request.Genres
import com.jbkalit.domain.model.request.Movies
import com.jbkalit.domain.model.request.Reviews
import com.jbkalit.domain.model.request.Videos
import com.jbkalit.domain.repository.MovieRepositoryContract
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject
constructor(private val movieRemoteDataSource: MovieRemoteDataSourceContract,
            private val movieService: MovieService) : MovieRepositoryContract {

    override fun getGenreList(): Single<Genres> {
        return movieRemoteDataSource.getGenreList()
    }

    override fun getMoviesByGenre(page: Int, id: Int): Flow<Movies> {
        return flow { emit(movieRemoteDataSource.getMoviesByGenre(page, id)) }
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

    override suspend fun getMovieByGenrePagingFlow(query: Int): Flow<PagingData<Movie>> {
        return Pager(config = getPageConfig(), pagingSourceFactory = {
            MoviesPagingSource(movieService, query)
        }).flow
    }

    private fun getPageConfig(): PagingConfig {
        return PagingConfig(pageSize = 20, initialLoadSize = 5, enablePlaceholders = false)
    }

}
