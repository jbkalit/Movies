package com.jbkalit.movies.presentation.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jbkalit.domain.model.Movie
import com.jbkalit.domain.usecase.MovieUseCaseContract
import com.jbkalit.movies.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieUseCase: MovieUseCaseContract)
    : BaseViewModel() {

    private val _movie = MutableLiveData<List<Movie>>()
    val movie: LiveData<List<Movie>>
        get() = _movie

    val isError = MutableLiveData(false)
    val isLoading = MutableLiveData(false)
    val isLoadMore = MutableLiveData(false)
    val isEmpty = MutableLiveData(false)

    var loadMoreError = MutableLiveData<String>()

    suspend fun fetchMovie(id: Int) : Flow<PagingData<Movie>> {
        return movieUseCase.getMovieByGenrePagingFlow(id).cachedIn(viewModelScope)
    }

}
