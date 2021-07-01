package com.jbkalit.movies.presentation.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.jbkalit.domain.model.Genre
import com.jbkalit.movies.databinding.FragmentListGenreBinding
import com.jbkalit.movies.presentation.ext.setGone
import com.jbkalit.movies.presentation.ext.setVisible
import com.jbkalit.movies.presentation.ext.showIf
import com.jbkalit.movies.presentation.genre.adapter.ViewPagerAdapter
import com.jbkalit.movies.presentation.genre.viewmodel.GenreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list_genre.*

@AndroidEntryPoint
class GenreFragment : Fragment() {

    private val genreViewModel: GenreViewModel by viewModels()

    private lateinit var binding: FragmentListGenreBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        genreViewModel.fetchGenre()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addTabs(binding.viewPager)
        setupObserver()
        setupErrorObserver()
        setupEmptyObserver()
        setupLoadingObserver()
    }

    private fun addTabs(viewPager: ViewPager) {
        viewPagerAdapter = ViewPagerAdapter(activity?.supportFragmentManager!!)
        viewPager.adapter = this.viewPagerAdapter
    }

    private fun setupObserver() {
        genreViewModel.genre.observe(viewLifecycleOwner, { response ->
            response?.let {
                emptyLayout.setGone()
                tabLayout.setVisible()
                configureGenreTabsWithData(it.genres, binding.viewPager)
            }
        })
    }

    private fun setupErrorObserver() {
        genreViewModel.isError.observe(viewLifecycleOwner, { response ->
            response?.let {
                errorLayout.showIf(it)
                if (it) {
                    tabLayout.setGone()
                    loadingLayout.setGone()
                    emptyLayout.setGone()
                }
            }
        })
    }

    private fun setupLoadingObserver() {
        genreViewModel.isLoading.observe(viewLifecycleOwner, { response ->
            response?.let {
                loadingLayout.showIf(it)
                if (it) {
                    tabLayout.setGone()
                    errorLayout.setGone()
                    emptyLayout.setGone()
                }

            }
        })
    }

    private fun setupEmptyObserver() {
        genreViewModel.isEmpty.observe(viewLifecycleOwner, { response ->
            response?.let {
                emptyLayout.showIf(it)
                if (it) {
                    tabLayout.setGone()
                    errorLayout.setGone()
                    loadingLayout.setGone()
                }
            }
        })
    }

    private fun configureGenreTabsWithData(genreList: List<Genre>?, viewPager: ViewPager?) {
        if (genreList != null && viewPager != null) {
            val newPageAdapter = ViewPagerAdapter(activity?.supportFragmentManager!!)
            for (genre: Genre in genreList) {
                newPageAdapter.add(genre.id!!, genre.name!!)
                viewPager.adapter = newPageAdapter
            }
            binding.tabLayout.setupWithViewPager(viewPager)
            viewPager.offscreenPageLimit = 0
        }
    }

}
