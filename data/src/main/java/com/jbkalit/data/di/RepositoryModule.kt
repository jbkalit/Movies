package com.jbkalit.data.di

import com.jbkalit.data.repository.MovieRepository
import com.jbkalit.data.service.MovieService
import com.jbkalit.data.source.remote.MovieRemoteDataSourceContract
import com.jbkalit.domain.repository.MovieRepositoryContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesMovieRepository(movieRemoteDataSource: MovieRemoteDataSourceContract,
                                movieService: MovieService)
            : MovieRepositoryContract = MovieRepository(movieRemoteDataSource, movieService)

}
