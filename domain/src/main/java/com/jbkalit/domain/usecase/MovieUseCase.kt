package com.jbkalit.domain.usecase

import com.jbkalit.domain.model.Movie
import com.jbkalit.domain.model.request.Genres
import com.jbkalit.domain.model.request.Movies
import com.jbkalit.domain.model.request.Reviews
import com.jbkalit.domain.model.request.Videos
import com.jbkalit.domain.repository.MovieRepositoryContract
import io.reactivex.Single
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val genreRepository: MovieRepositoryContract)
    : MovieUseCaseContract {

    override fun getGenreList() : Single<Genres> {
        return genreRepository.getGenreList()
    }

    override fun getMoviesByGenre(page: Int, id: Int): Single<Movies> {
        return genreRepository.getMoviesByGenre(page, id)
    }

    override fun getMovieById(id: Int): Single<Movie> {
        return genreRepository.getMovieById(id)
    }

    override fun getReviewByMovieId(id: Int, page: Int): Single<Reviews> {
        return genreRepository.getReviewByMovieId(id, page)
    }

    override fun getVideoByMovieId(id: Int): Single<Videos> {
        return genreRepository.getVideoByMovieId(id)
    }

}
