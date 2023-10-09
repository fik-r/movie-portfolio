package com.example.myapplication.ui.search

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.ui.movie.MovieFragment
import java.io.InvalidClassException

class PagerAdapter(fm: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MovieFragment(MovieFragment.MOVIE_POPULAR)
            1 -> MovieFragment(MovieFragment.MOVIE_FAVORITE)
            else -> throw InvalidClassException("Invalid Index")
        }
    }
}