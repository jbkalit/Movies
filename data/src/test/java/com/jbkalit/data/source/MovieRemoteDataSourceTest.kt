package com.jbkalit.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbkalit.data.mock.*
import com.jbkalit.data.scheduler.BaseSchedulerProvider
import com.jbkalit.data.scheduler.SchedulerProvider
import com.jbkalit.data.service.MovieService
import com.jbkalit.data.source.remote.MovieRemoteDataSource
import com.jbkalit.data.source.remote.MovieRemoteDataSourceContract
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MovieRemoteDataSourceTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieService: MovieService

    private lateinit var schedulerProvider: BaseSchedulerProvider
    private lateinit var movieRemoteDataSource: MovieRemoteDataSourceContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        schedulerProvider = SchedulerProvider.getInstance()

        movieRemoteDataSource = MovieRemoteDataSource(movieService, schedulerProvider)
    }

    @Test
    fun getGenre_Success_Test() {
        Mockito.`when`(movieService.getGenreList())
            .thenReturn(Single.just(genres))

        movieRemoteDataSource.getGenreList()

        Mockito.verify(movieService, Mockito.times(1)).getGenreList()
        Assert.assertNotNull("Genre not empty", movieRemoteDataSource.getGenreList())
    }

    @Test
    fun getMovie_Success_Test() {
        Mockito.`when`(movieService.getMovieById(genre.id!!))
            .thenReturn(Single.just(movie))

        movieRemoteDataSource.getMovieById(genre.id!!)

        Mockito.verify(movieService, Mockito.times(1)).getMovieById(genre.id!!)
        Assert.assertNotNull("Movie not empty", movieRemoteDataSource.getMovieById(genre.id!!))
    }

    @Test
    fun getMovieByGenre_Success_Test() {
        Mockito.`when`(movieService.getMoviesByGenre(1, genre.id!!))
            .thenReturn(Single.just(movies))

        movieRemoteDataSource.getMoviesByGenre(1, genre.id!!)

        Mockito.verify(movieService, Mockito.times(1)).getMoviesByGenre(1, genre.id!!)
        Assert.assertNotNull("Movie not empty", movieRemoteDataSource.getMoviesByGenre(1, genre.id!!))
    }

    @Test
    fun getReview_Success_Test() {
        Mockito.`when`(movieService.getReviewByMovieId(genre.id!!, 1))
            .thenReturn(Single.just(reviews))

        movieRemoteDataSource.getReviewByMovieId(genre.id!!, 1)

        Mockito.verify(movieService, Mockito.times(1)).getReviewByMovieId(genre.id!!, 1)
        Assert.assertNotNull("Review not empty", movieRemoteDataSource.getReviewByMovieId(genre.id!!, 1))
    }

    @Test
    fun getVideo_Success_Test() {
        Mockito.`when`(movieService.getVideoByMovieId(genre.id!!))
            .thenReturn(Single.just(videos))

        movieRemoteDataSource.getVideoByMovieId(genre.id!!)

        Mockito.verify(movieService, Mockito.times(1)).getVideoByMovieId(genre.id!!)
        Assert.assertNotNull("Video not empty", movieRemoteDataSource.getVideoByMovieId(genre.id!!))
    }

}
