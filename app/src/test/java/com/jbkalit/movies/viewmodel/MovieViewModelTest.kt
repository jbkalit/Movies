package com.jbkalit.movies.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbkalit.domain.model.request.Movies
import com.jbkalit.domain.usecase.MovieUseCaseContract
import com.jbkalit.movies.mock.*
import com.jbkalit.movies.presentation.movie.viewmodel.MovieViewModel
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MovieViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private var testMovie: Single<Movies>? = null

    @Mock
    private lateinit var movieUseCase: MovieUseCaseContract

    private lateinit var movieViewModel: MovieViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        movieViewModel = MovieViewModel(movieUseCase)
    }

    @Test
    fun getMovie_Success_Test() {
        testMovie = Single.just(movies)
        `when`(movieUseCase.getMoviesByGenre(1, genre.id!!)).thenReturn(testMovie)
        movieViewModel.fetchMovie(genre.id!!)
        Assert.assertEquals(1, movieViewModel.movie.value?.size)
        Assert.assertEquals(false, movieViewModel.isError.value)
        Assert.assertEquals(false, movieViewModel.isLoading.value)
    }

    @Test
    fun getMovie_ErrorShow_Test() {
        testMovie = Single.error(Throwable())
        `when`(movieUseCase.getMoviesByGenre(1, genre.id!!)).thenReturn(testMovie)
        movieViewModel.fetchMovie(genre.id!!)
        Assert.assertEquals(true, movieViewModel.isError.value)
    }

    @Test
    fun getMovie_LoadingShow_Test() {
        testMovie = Single.never()
        `when`(movieUseCase.getMoviesByGenre(1, genre.id!!)).thenReturn(testMovie)
        movieViewModel.fetchMovie(genre.id!!)
        Assert.assertEquals(true, movieViewModel.isLoading.value)
    }

}
