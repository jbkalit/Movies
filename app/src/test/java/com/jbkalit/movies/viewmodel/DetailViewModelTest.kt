package com.jbkalit.movies.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbkalit.domain.model.Movie
import com.jbkalit.domain.model.request.Reviews
import com.jbkalit.domain.model.request.Videos
import com.jbkalit.domain.usecase.MovieUseCaseContract
import com.jbkalit.movies.mock.*
import com.jbkalit.movies.presentation.detail.viewmodel.DetailViewModel
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class DetailViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private var testMovie: Single<Movie>? = null
    private var testTrailer: Single<Videos>? = null
    private var testReview: Single<Reviews>? = null

    @Mock
    private lateinit var movieUseCase: MovieUseCaseContract

    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        detailViewModel = DetailViewModel(movieUseCase)
    }

    @Test
    fun getMovie_Success_Test() {
        testMovie = Single.just(movie)
        `when`(movieUseCase.getMovieById(movie.id)).thenReturn(testMovie)
        detailViewModel.fetchMovie(movie.id)
        Assert.assertEquals(movie, detailViewModel.movie.value)
    }

    @Test
    fun getMovie_ErrorShow_Test() {
        testMovie = Single.error(Throwable())
        `when`(movieUseCase.getMovieById(movie.id)).thenReturn(testMovie)
        detailViewModel.fetchMovie(movie.id)
        Assert.assertEquals(true, detailViewModel.isError.value)
    }

    @Test
    fun getMovie_LoadingShow_Test() {
        testMovie = Single.never()
        `when`(movieUseCase.getMovieById(movie.id)).thenReturn(testMovie)
        detailViewModel.fetchMovie(movie.id)
        Assert.assertEquals(true, detailViewModel.isLoading.value)
    }

    @Test
    fun getTrailer_Success_Test() {
        testTrailer = Single.just(videos)
        `when`(movieUseCase.getVideoByMovieId(movie.id)).thenReturn(testTrailer)
        detailViewModel.fetchVideos(movie.id)
        Assert.assertEquals(videos.results, detailViewModel.video.value)
    }

    @Test
    fun getReview_Success_Test() {
        testReview = Single.just(reviews)
        `when`(movieUseCase.getReviewByMovieId(movie.id, 1)).thenReturn(testReview)
        detailViewModel.fetchReviews(movie.id)
        Assert.assertEquals(reviews.results, detailViewModel.review.value)
    }

}
