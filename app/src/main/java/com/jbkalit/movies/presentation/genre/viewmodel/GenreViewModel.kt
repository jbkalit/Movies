package com.jbkalit.movies.presentation.genre.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jbkalit.domain.model.request.Genres
import com.jbkalit.domain.usecase.MovieUseCaseContract
import com.jbkalit.movies.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(private val movieUseCase: MovieUseCaseContract) :
    BaseViewModel() {

    val isError = MutableLiveData(false)
    val isLoading = MutableLiveData(false)
    val isEmpty = MutableLiveData(false)

    private val _genre = MutableLiveData<Genres>()
    val genre: LiveData<Genres>
        get() = _genre

    fun fetchGenre() {
        isLoading.value = true
        disposable = movieUseCase.getGenreList()
            .subscribeWith(object : DisposableSingleObserver<Genres>() {
                override fun onSuccess(genres: Genres) {
                    isLoading.value = false
                    isError.value = false
                    _genre.value = genres
                }

                override fun onError(e: Throwable) {
                    isLoading.value = false
                    isError.value = true
                }
            })
    }

}
