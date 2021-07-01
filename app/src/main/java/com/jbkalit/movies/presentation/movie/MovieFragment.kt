package com.jbkalit.movies.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jbkalit.movies.databinding.FragmentListMovieBinding
import com.jbkalit.movies.presentation.ext.setGone
import com.jbkalit.movies.presentation.ext.setVisible
import com.jbkalit.movies.presentation.ext.showIf
import com.jbkalit.movies.presentation.ext.showSnackbar
import com.jbkalit.movies.presentation.genre.GenreFragmentDirections
import com.jbkalit.movies.presentation.movie.adapter.MovieAdapter
import com.jbkalit.movies.presentation.movie.viewmodel.MovieViewModel
import com.jbkalit.movies.util.navigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list_movie.*

@AndroidEntryPoint
class MovieFragment : Fragment(), MovieAdapter.OnMovieClickedListener {

    private val movieViewModel: MovieViewModel by viewModels()

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var binding: FragmentListMovieBinding
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getInt(MOVIE_GENRE_ID)?.let { movieViewModel.fetchMovie(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupErrorObserver()
        setupEmptyObserver()
        setupLoadingObserver()
        setupObserver()
        setupLoadMoreObserver()
        setupLoadMoreErrorObserver()
    }

    private fun setupView() {
        emptyLayout.setVisible()
        with(binding) {
            gridLayoutManager = GridLayoutManager(activity, calculateNoOfColumns())
            movieRecyclerView.layoutManager = gridLayoutManager
            movieRecyclerView.setHasFixedSize(true)
            movieRecyclerView.itemAnimator = DefaultItemAnimator()
            adapter = MovieAdapter(mutableListOf(), this@MovieFragment)
            movieRecyclerView.adapter = adapter

            movieRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        val visibleItemCount = gridLayoutManager.childCount
                        val pastVisibleItem =
                            gridLayoutManager.findLastCompletelyVisibleItemPosition()
                        val total = adapter.itemCount
                        if ((visibleItemCount + pastVisibleItem) >= total) {
                            arguments?.getInt(MOVIE_GENRE_ID)?.let { movieViewModel.loadMore(it) }
                        }
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
        }
    }


    private fun setupObserver() {
        movieViewModel.movie.observe(viewLifecycleOwner, { response ->
            response?.let {
                emptyLayout.setGone()
                movieRecyclerView.setVisible()
                adapter.addToList(it.toMutableList())
            }
        })
    }

    private fun setupErrorObserver() {
        movieViewModel.isError.observe(viewLifecycleOwner, { response ->
            response?.let {
                errorLayout.showIf(it)
                if (it) {
                    movieRecyclerView.setGone()
                    loadingLayout.setGone()
                    emptyLayout.setGone()
                }
            }
        })
    }

    private fun setupLoadingObserver() {
        movieViewModel.isLoading.observe(viewLifecycleOwner, { response ->
            response?.let {
                loadingLayout.showIf(it)
                if (it) {
                    movieRecyclerView.setGone()
                    errorLayout.setGone()
                    emptyLayout.setGone()
                }

            }
        })
    }

    private fun setupEmptyObserver() {
        movieViewModel.isEmpty.observe(viewLifecycleOwner, { response ->
            response?.let {
                emptyLayout.showIf(it)
                if (it) {
                    movieRecyclerView.setGone()
                    errorLayout.setGone()
                    loadingLayout.setGone()
                }
            }
        })
    }

    private fun setupLoadMoreObserver() {
        movieViewModel.isLoadMore.observe(viewLifecycleOwner, { response ->
            response?.let {
                loadMore.showIf(it)

            }
        })
    }

    private fun setupLoadMoreErrorObserver() {
        movieViewModel.loadMoreError.observe(viewLifecycleOwner, { response ->
            response?.let {
                view?.showSnackbar(it)

            }
        })
    }

    private fun calculateNoOfColumns(): Int {
        val displayMetrics = this@MovieFragment.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        return (dpWidth / 120).toInt()
    }

    override fun onMovieClick(movieId: Int) {
        navigate(GenreFragmentDirections.actionGenreFragmentToDetailFragment(movieId))
    }

    companion object {

        private const val MOVIE_GENRE_ID = "MOVIE_GENRE_ID"

        fun newInstance(id: Int): MovieFragment {
            val fragment = MovieFragment()
            val args = Bundle()
            args.putInt(MOVIE_GENRE_ID, id)
            fragment.arguments = args
            return fragment
        }

    }

}
