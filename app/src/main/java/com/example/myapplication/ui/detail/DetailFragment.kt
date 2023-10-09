package com.example.myapplication.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.BuildConfig
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.template.InfoTemplateAdapter
import com.example.myapplication.template.InfoTitleDescription
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var infoTemplateAdapter: InfoTemplateAdapter

    private val args: DetailFragmentArgs by navArgs()
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentDetailBinding.inflate(LayoutInflater.from(requireActivity()), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initData()
        subscribeLiveData()
    }

    private fun initViews() {
        infoTemplateAdapter = InfoTemplateAdapter()
        with(binding) {
            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
            listInfo.apply {
                adapter = infoTemplateAdapter
                layoutManager =
                    LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            }
            btnFavorite.isEnabled = false
            btnFavorite.setOnClickListener {
                detailViewModel.user.value?.let {
                    if (btnFavorite.isChecked)
                        detailViewModel.insertFavorite(it)
                    else
                        detailViewModel.deleteFavorite(it)
                }
            }
        }
    }

    private fun initData() {
        detailViewModel.fetchMovies(args.username)
    }

    private fun subscribeLiveData() {
        detailViewModel.user.observe(requireActivity()) {
            detailViewModel.findFavoriteMovies(it.id.toInt())
            binding.btnFavorite.isEnabled = true
            Glide.with(requireActivity()).load(BuildConfig.APP_SERVICE_IMAGE_BASE_URL + it.backgroundImage).into(binding.ivImage)
            infoTemplateAdapter.apply {
                listInfo.clear()
                listInfo.addAll(
                    listOf(
                        InfoTitleDescription(
                            "Name", it.name
                        ),
                        InfoTitleDescription(
                            "Released", it.released
                        ),
                        InfoTitleDescription(
                            "Popularity", it.rating
                        ),
                    )
                )
                notifyDataSetChanged()
            }
        }
        detailViewModel.toggleFavorite.observe(requireActivity()) {
            binding.btnFavorite.isChecked = it
        }
    }
}