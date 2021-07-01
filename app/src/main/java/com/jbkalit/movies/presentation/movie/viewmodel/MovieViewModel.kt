package com.jbkalit.movies.presentation.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jbkalit.domain.model.Movie
import com.jbkalit.domain.model.request.Movies
import com.jbkalit.domain.usecase.MovieUseCaseContract
import com.jbkalit.movies.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieUseCase: MovieUseCaseContract)
    : BaseViewModel() {

    private var page = 1

    private val _movie = MutableLiveData<List<Movie>>()
    val movie: LiveData<List<Movie>>
        get() = _movie

    val isError = MutableLiveData(false)
    val isLoading = MutableLiveData(false)
    val isLoadMore = MutableLiveData(false)
    val isEmpty = MutableLiveData(false)

    var loadMoreError = MutableLiveData<String>()

    fun fetchMovie(id: Int) {
        isLoading.value = true
        disposable = movieUseCase.getMoviesByGenre(page, id)
            .subscribeWith(object : DisposableSingleObserver<Movies>() {
                override fun onSuccess(movies: Movies) {
                    isLoading.value = false
                    isError.value = false
                    _movie.value = movies.results!!
                }
                override fun onError(e: Throwable) {
                    isLoading.value = false
                    isError.value = true
                }
            })
        page++
    }

    fun loadMore(id: Int) {
        isLoadMore.value = true
        disposable = movieUseCase.getMoviesByGenre(page, id)
            .subscribeWith(object : DisposableSingleObserver<Movies>(){
                override fun onSuccess(movies: Movies) {
                    _movie.value = movies.results!!
                    isLoadMore.value = false
                }

                override fun onError(e: Throwable) {
                    isLoadMore.value = false
                    loadMoreError.value = e.message
                }
            })
        page++
    }

}
