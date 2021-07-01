package com.jbkalit.movies.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbkalit.domain.model.request.Genres
import com.jbkalit.domain.usecase.MovieUseCaseContract
import com.jbkalit.movies.mock.genres
import com.jbkalit.movies.presentation.genre.viewmodel.GenreViewModel
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GenreViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private var testGenre: Single<Genres>? = null

    @Mock
    private lateinit var movieUseCase: MovieUseCaseContract

    private lateinit var genreViewModel: GenreViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        genreViewModel = GenreViewModel(movieUseCase)
    }

    @Test
    fun getGenre_Success_Test() {
        testGenre = Single.just(genres)
        `when`(movieUseCase.getGenreList()).thenReturn(testGenre)
        genreViewModel.fetchGenre()
        Assert.assertEquals(1, genreViewModel.genre.value?.genres?.size)
        Assert.assertEquals(false, genreViewModel.isError.value)
        Assert.assertEquals(false, genreViewModel.isLoading.value)
    }

    @Test
    fun getGenre_ErrorShow_Test() {
        testGenre = Single.error(Throwable())
        `when`(movieUseCase.getGenreList()).thenReturn(testGenre)
        genreViewModel.fetchGenre()
        Assert.assertEquals(true, genreViewModel.isError.value)
    }

    @Test
    fun getGenre_LoadingShow_Test() {
        testGenre = Single.never()
        `when`(movieUseCase.getGenreList()).thenReturn(testGenre)
        genreViewModel.fetchGenre()
        Assert.assertEquals(true, genreViewModel.isLoading.value)
    }

}
