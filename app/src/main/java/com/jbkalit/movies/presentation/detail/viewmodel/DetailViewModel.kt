package com.jbkalit.movies.presentation.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jbkalit.domain.model.Movie
import com.jbkalit.domain.model.Review
import com.jbkalit.domain.model.Video
import com.jbkalit.domain.model.request.Reviews
import com.jbkalit.domain.model.request.Videos
import com.jbkalit.domain.usecase.MovieUseCaseContract
import com.jbkalit.movies.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCaseContract) :
    BaseViewModel() {

    private var page = 1

    val isError = MutableLiveData(false)
    val isLoading = MutableLiveData(false)
    val isEmpty = MutableLiveData(false)

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    private val _review = MutableLiveData<List<Review>>()
    val review: LiveData<List<Review>>
        get() = _review

    private val _video = MutableLiveData<List<Video>>()
    val video: LiveData<List<Video>>
        get() = _video

    fun fetchMovie(id: Int) {
        isLoading.value = true
        disposable = movieUseCase.getMovieById(id)
            .subscribeWith(object : DisposableSingleObserver<Movie>() {
                override fun onSuccess(movie: Movie) {
                    isLoading.value = false
                    isError.value = false
                    _movie.value = movie
                }

                override fun onError(e: Throwable) {
                    isLoading.value = false
                    isError.value = true
                }
            })
    }

    fun fetchReviews(id: Int) {
        isLoading.value = true
        disposable = movieUseCase.getReviewByMovieId(id, page)
            .subscribeWith(object : DisposableSingleObserver<Reviews>() {
                override fun onSuccess(reviews: Reviews) {
                    reviews.results?.let {
                        isLoading.value = false
                        isError.value = false
                        _review.value = it
                    }
                }

                override fun onError(e: Throwable) {
                    isLoading.value = false
                    isError.value = true
                }
            })
        page++
    }

    fun fetchVideos(id: Int) {
        isLoading.value = true
        disposable = movieUseCase.getVideoByMovieId(id)
            .subscribeWith(object : DisposableSingleObserver<Videos>() {
                override fun onSuccess(videos: Videos) {
                    videos.results?.let {
                        isLoading.value = false
                        isError.value = false
                        _video.value = it
                    }
                }

                override fun onError(e: Throwable) {
                    isLoading.value = false
                    isError.value = true
                }
            })
    }

    fun loadMore(id: Int) {
        disposable = movieUseCase.getReviewByMovieId(id, page)
            .subscribeWith(object : DisposableSingleObserver<Reviews>() {
                override fun onSuccess(reviews: Reviews) {
                    reviews.results?.let {
                        isLoading.value = false
                        isError.value = false
                        _review.value = it
                    }
                }

                override fun onError(e: Throwable) {
                    isLoading.value = false
                    isError.value = true
                }
            })
        page++
    }

}
