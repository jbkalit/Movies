package com.jbkalit.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbkalit.domain.mock.*
import com.jbkalit.domain.repository.MovieRepositoryContract
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MovieUcaCaseTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepositoryContract

    private lateinit var movieUseCase: MovieUseCaseContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        movieUseCase = MovieUseCase(movieRepository)
    }

    @Test
    fun getGenre_Success_Test() {
        Mockito.`when`(movieRepository.getGenreList())
            .thenReturn(Single.just(genres))

        movieUseCase.getGenreList()

        Mockito.verify(movieRepository, Mockito.times(1)).getGenreList()
        Assert.assertNotNull("Genre not empty", movieUseCase.getGenreList())
    }

    @Test
    fun getMovie_Success_Test() {
        Mockito.`when`(movieRepository.getMovieById(genre.id!!))
            .thenReturn(Single.just(movie))

        movieUseCase.getMovieById(genre.id!!)

        Mockito.verify(movieRepository, Mockito.times(1)).getMovieById(genre.id!!)
        Assert.assertNotNull("Movie not empty", movieUseCase.getMovieById(genre.id!!))
    }

    @Test
    fun getMovieByGenre_Success_Test() {
        Mockito.`when`(movieRepository.getMoviesByGenre(1, genre.id!!))
            .thenReturn(Single.just(movies))

        movieUseCase.getMoviesByGenre(1, genre.id!!)

        Mockito.verify(movieRepository, Mockito.times(1)).getMoviesByGenre(1, genre.id!!)
        Assert.assertNotNull("Movie not empty", movieUseCase.getMoviesByGenre(1, genre.id!!))
    }

    @Test
    fun getReview_Success_Test() {
        Mockito.`when`(movieRepository.getReviewByMovieId(genre.id!!, 1))
            .thenReturn(Single.just(reviews))

        movieUseCase.getReviewByMovieId(genre.id!!, 1)

        Mockito.verify(movieRepository, Mockito.times(1)).getReviewByMovieId(genre.id!!, 1)
        Assert.assertNotNull("Review not empty", movieUseCase.getReviewByMovieId(genre.id!!, 1))
    }

    @Test
    fun getVideo_Success_Test() {
        Mockito.`when`(movieRepository.getVideoByMovieId(genre.id!!))
            .thenReturn(Single.just(videos))

        movieUseCase.getVideoByMovieId(genre.id!!)

        Mockito.verify(movieRepository, Mockito.times(1)).getVideoByMovieId(genre.id!!)
        Assert.assertNotNull("Video not empty", movieUseCase.getVideoByMovieId(genre.id!!))
    }

}
