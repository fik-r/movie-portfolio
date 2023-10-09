package com.example.myapplication.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentMovieBinding
import com.example.myapplication.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment(private val type: String) : Fragment() {
    companion object {
        const val MOVIE_POPULAR = "MOVIE_POPULAR"
        const val MOVIE_FAVORITE = "MOVIE_FAVORITE"
    }

    private lateinit var binding: FragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initData()
        subscribeLiveData()
    }

    private fun initViews() {
        with(binding) {
            movieAdapter = MovieAdapter {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
                findNavController().navigate(action)
            }
            listUser.apply {
                adapter = movieAdapter
                layoutManager =
                    LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                addItemDecoration(
                    DividerItemDecoration(
                        requireActivity(),
                        LinearLayoutManager.VERTICAL
                    )
                )
            }
            refresh.setOnRefreshListener {
                refresh.isRefreshing = false
                initData()
            }
        }
    }

    private fun initData() {
        if (type == MOVIE_POPULAR)
            movieViewModel.fetchMovies() else
            movieViewModel.findFavoriteUsers()
    }

    private fun subscribeLiveData() {
        with(movieViewModel) {
            isLoading.observe(requireActivity()) {
                binding.loading.isVisible = it
            }
            listMovies.observe(requireActivity()) {
                movieAdapter.apply {
                    listUser.clear()
                    listUser.addAll(it)
                    notifyDataSetChanged()
                }
            }
        }
    }
}