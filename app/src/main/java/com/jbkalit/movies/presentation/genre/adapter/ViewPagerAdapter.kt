package com.jbkalit.movies.presentation.genre.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.jbkalit.movies.presentation.movie.MovieFragment

class ViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    private val fragmentList: MutableList<Pair<Int, String>> = ArrayList()

    fun add(id: Int, title: String) {
        fragmentList.add(Pair(id, title))
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragmentList[position].second
    }

    override fun getItem(position: Int): Fragment {
        return MovieFragment.newInstance(fragmentList[position].first)
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

}
