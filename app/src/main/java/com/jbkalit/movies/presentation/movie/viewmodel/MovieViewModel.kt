package com.jbkalit.movies.presentation.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jbkalit.domain.model.Movie
import com.jbkalit.domain.usecase.MovieUseCaseContract
import com.jbkalit.movies.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
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
        viewModelScope.launch {
           movieUseCase.getMoviesByGenre(page, id)
               .onStart {
                   isLoading.value = true
                   isError.value = false
               }
               .catch {
                   isLoading.value = false
                   isError.value = true
               }
               .collect {
                   isLoading.value = false
                   isError.value = false
                   it.results.let { movies ->
                       _movie.value = movies
                   }
               }
        }
        page++
    }

    fun loadMore(id: Int) {
        viewModelScope.launch {
            movieUseCase.getMoviesByGenre(page, id)
                .onStart {
                    isLoading.value = true
                    isError.value = false
                }
                .catch {
                    isLoading.value = false
                    isError.value = true
                }
                .collect {
                    isLoading.value = false
                    isError.value = false
                    it.results.let { movies ->
                        _movie.value = movies
                    }
                }
        }
        page++
    }

}
