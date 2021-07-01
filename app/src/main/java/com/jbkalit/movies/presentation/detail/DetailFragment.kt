package com.jbkalit.movies.presentation.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jbkalit.data.util.BASE_URL_POSTER_SIZE_ORIGINAL
import com.jbkalit.movies.R
import com.jbkalit.movies.databinding.FragmentDetailMovieBinding
import com.jbkalit.movies.presentation.detail.adapter.ReviewAdapter
import com.jbkalit.movies.presentation.detail.adapter.VideoAdapter
import com.jbkalit.movies.presentation.detail.viewmodel.DetailViewModel
import com.jbkalit.movies.presentation.ext.setGone
import com.jbkalit.movies.presentation.ext.setVisible
import com.jbkalit.movies.presentation.ext.showIf
import com.jbkalit.movies.util.Helper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import kotlinx.android.synthetic.main.fragment_detail_movie.emptyLayout
import kotlinx.android.synthetic.main.fragment_detail_movie.errorLayout
import kotlinx.android.synthetic.main.fragment_detail_movie.loadingLayout
import kotlinx.android.synthetic.main.fragment_list_genre.*

@AndroidEntryPoint
class DetailFragment : Fragment(), VideoAdapter.OnVideoClickedListener {

    private val detailViewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding: FragmentDetailMovieBinding
    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var videoAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailViewModel.fetchMovie(args.id)
        detailViewModel.fetchReviews(args.id)
        detailViewModel.fetchVideos(args.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupView()
        setupObserver()
        setupReviewObserver()
        setupVideoObserver()
        setupErrorObserver()
        setupEmptyObserver()
        setupLoadingObserver()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                activity?.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupView() {
        with(binding) {
            videoRecyclerView.layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            videoAdapter = VideoAdapter(mutableListOf(), this@DetailFragment)
            videoRecyclerView.adapter = videoAdapter

            reviewRecyclerView.layoutManager = LinearLayoutManager(activity)
            reviewAdapter = ReviewAdapter(mutableListOf())
            reviewRecyclerView.adapter = reviewAdapter

            reviewRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        val visibleItemCount = linearLayoutManager.childCount
                        val pastVisibleItem =
                            linearLayoutManager.findLastCompletelyVisibleItemPosition()
                        val total = reviewAdapter.itemCount
                        if ((visibleItemCount + pastVisibleItem) >= total) {
                            arguments?.getInt(args.id.toString())
                                ?.let { detailViewModel.loadMore(it) }
                        }
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
        }
    }

    private fun setupObserver() {
        detailViewModel.movie.observe(viewLifecycleOwner, { response ->
            response?.let {
                emptyLayout.setGone()
                contentLayout.setVisible()
                with(binding) {
                    Glide.with(this@DetailFragment)
                        .asBitmap()
                        .load(BASE_URL_POSTER_SIZE_ORIGINAL + it.backdropPath)
                        .into(backdrop)
                    Glide.with(this@DetailFragment)
                        .asBitmap()
                        .load(BASE_URL_POSTER_SIZE_ORIGINAL + it.posterPath)
                        .into(poster)
                    title.text = it.originalTitle
                    date.text = it.releaseDate
                    overview.text = it.overview
                }
            }
        })
    }

    private fun setupReviewObserver() {
        detailViewModel.review.observe(viewLifecycleOwner, { response ->
            response?.let {
                reviewAdapter.addToList(it.toMutableList())
            }
        })
    }

    private fun setupVideoObserver() {
        detailViewModel.video.observe(viewLifecycleOwner, { response ->
            response?.let {
                videoAdapter.addToList(it.toMutableList())
            }
        })
    }

    private fun setupErrorObserver() {
        detailViewModel.isError.observe(viewLifecycleOwner, { response ->
            response?.let {
                errorLayout.showIf(it)
                if (it) {
                    contentLayout.setGone()
                    loadingLayout.setGone()
                    emptyLayout.setGone()
                }
            }
        })
    }

    private fun setupLoadingObserver() {
        detailViewModel.isLoading.observe(viewLifecycleOwner, { response ->
            response?.let {
                loadingLayout.showIf(it)
                if (it) {
                    contentLayout.setGone()
                    errorLayout.setGone()
                    emptyLayout.setGone()
                }

            }
        })
    }

    private fun setupEmptyObserver() {
        detailViewModel.isEmpty.observe(viewLifecycleOwner, { response ->
            response?.let {
                emptyLayout.showIf(it)
                if (it) {
                    contentLayout.setGone()
                    errorLayout.setGone()
                    loadingLayout.setGone()
                }
            }
        })
    }

    override fun onVideoClick(key: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(Helper.buildYoutubeURL(key))
        startActivity(Intent.createChooser(intent, "Watch Video"))
    }

}
