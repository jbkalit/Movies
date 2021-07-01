package com.jbkalit.domain.di

import com.jbkalit.domain.repository.MovieRepositoryContract
import com.jbkalit.domain.usecase.MovieUseCase
import com.jbkalit.domain.usecase.MovieUseCaseContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun providesUserUseCasePost(movieRepository: MovieRepositoryContract)
            : MovieUseCaseContract = MovieUseCase(movieRepository)

}
