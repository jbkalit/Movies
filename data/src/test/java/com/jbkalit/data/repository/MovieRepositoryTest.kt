package com.jbkalit.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbkalit.data.mock.*
import com.jbkalit.data.source.remote.MovieRemoteDataSourceContract
import com.jbkalit.domain.repository.MovieRepositoryContract
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MovieRepositoryTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRemoteDataSource: MovieRemoteDataSourceContract

    private lateinit var movieRepository: MovieRepositoryContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        movieRepository = MovieRepository(movieRemoteDataSource)
    }

    @Test
    fun getGenre_Success_Test() {
        Mockito.`when`(movieRemoteDataSource.getGenreList())
            .thenReturn(Single.just(genres))

        movieRepository.getGenreList()

        Mockito.verify(movieRemoteDataSource, Mockito.times(1)).getGenreList()
        Assert.assertNotNull("Genre not empty", movieRepository.getGenreList())
    }

    @Test
    fun getMovie_Success_Test() {
        Mockito.`when`(movieRemoteDataSource.getMovieById(genre.id!!))
            .thenReturn(Single.just(movie))

        movieRepository.getMovieById(genre.id!!)

        Mockito.verify(movieRemoteDataSource, Mockito.times(1)).getMovieById(genre.id!!)
        Assert.assertNotNull("Movie not empty", movieRepository.getMovieById(genre.id!!))
    }

    @Test
    fun getMovieByGenre_Success_Test() {
        Mockito.`when`(movieRemoteDataSource.getMoviesByGenre(1, genre.id!!))
            .thenReturn(Single.just(movies))

        movieRepository.getMoviesByGenre(1, genre.id!!)

        Mockito.verify(movieRemoteDataSource, Mockito.times(1)).getMoviesByGenre(1, genre.id!!)
        Assert.assertNotNull("Movie not empty", movieRepository.getMoviesByGenre(1, genre.id!!))
    }

    @Test
    fun getReview_Success_Test() {
        Mockito.`when`(movieRemoteDataSource.getReviewByMovieId(genre.id!!, 1))
            .thenReturn(Single.just(reviews))

        movieRepository.getReviewByMovieId(genre.id!!, 1)

        Mockito.verify(movieRemoteDataSource, Mockito.times(1)).getReviewByMovieId(genre.id!!, 1)
        Assert.assertNotNull("Review not empty", movieRepository.getReviewByMovieId(genre.id!!, 1))
    }

    @Test
    fun getVideo_Success_Test() {
        Mockito.`when`(movieRemoteDataSource.getVideoByMovieId(genre.id!!))
            .thenReturn(Single.just(videos))

        movieRepository.getVideoByMovieId(genre.id!!)

        Mockito.verify(movieRemoteDataSource, Mockito.times(1)).getVideoByMovieId(genre.id!!)
        Assert.assertNotNull("Video not empty", movieRepository.getVideoByMovieId(genre.id!!))
    }

}
