package com.jbkalit.data.di

import com.jbkalit.data.scheduler.BaseSchedulerProvider
import com.jbkalit.data.service.MovieService
import com.jbkalit.data.source.remote.MovieRemoteDataSource
import com.jbkalit.data.source.remote.MovieRemoteDataSourceContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SourceModule {

    @Provides
    @Singleton
    fun providesUserRemoteDataSource(movieService: MovieService,
                                     schedulerProvider: BaseSchedulerProvider)
            : MovieRemoteDataSourceContract = MovieRemoteDataSource(movieService, schedulerProvider)

}
