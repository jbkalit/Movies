package com.jbkalit.domain.usecase

import androidx.paging.PagingData
import com.jbkalit.domain.model.Movie
import com.jbkalit.domain.model.request.Genres
import com.jbkalit.domain.model.request.Movies
import com.jbkalit.domain.model.request.Reviews
import com.jbkalit.domain.model.request.Videos
import com.jbkalit.domain.repository.MovieRepositoryContract
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val movieRepository: MovieRepositoryContract)
    : MovieUseCaseContract {

    override fun getGenreList() : Single<Genres> {
        return movieRepository.getGenreList()
    }

    override suspend fun getMoviesByGenre(page: Int, id: Int): Flow<Movies> {
        return movieRepository.getMoviesByGenre(page, id)
    }

    override fun getMovieById(id: Int): Single<Movie> {
        return movieRepository.getMovieById(id)
    }

    override fun getReviewByMovieId(id: Int, page: Int): Single<Reviews> {
        return movieRepository.getReviewByMovieId(id, page)
    }

    override fun getVideoByMovieId(id: Int): Single<Videos> {
        return movieRepository.getVideoByMovieId(id)
    }

    override suspend fun getMovieByGenrePagingFlow(query: Int): Flow<PagingData<Movie>> {
        return movieRepository.getMovieByGenrePagingFlow(query)
    }

}
