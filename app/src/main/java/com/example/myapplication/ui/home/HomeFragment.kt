package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.search.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val titleTabs = arrayOf("Popular", "Favorite")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(binding) {
            pager.adapter = PagerAdapter(childFragmentManager, lifecycle)
            TabLayoutMediator(tabLayout, pager) { tab, position ->
                tab.text = titleTabs[position]
            }.attach()

            btnSearch.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
            }
        }
    }
}