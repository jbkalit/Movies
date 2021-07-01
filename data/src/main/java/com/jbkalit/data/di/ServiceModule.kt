package com.jbkalit.data.di

import com.jbkalit.data.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun providesMovieService(retrofit: Retrofit)
            : MovieService = retrofit.create(MovieService::class.java)

}
