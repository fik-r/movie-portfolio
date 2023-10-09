package com.example.myapplication.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.model.Movie
import com.example.myapplication.BuildConfig
import com.example.myapplication.databinding.ItemMovieBinding

class SearchAdapter(private val onItemClick: (userId: String) -> Unit) : PagingDataAdapter<Movie, SearchAdapter.ViewHolder>(
    DataDifferentiator
) {

    class ViewHolder(private val binding: ItemMovieBinding, private val onItemClick: (userId: String) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(movie: Movie) {
            with(binding) {
                tvTitle.text = movie.name
                root.setOnClickListener {
                    onItemClick.invoke(movie.id)
                }
                Glide.with(root).load(BuildConfig.APP_SERVICE_IMAGE_BASE_URL + movie.backgroundImage).into(ivImage)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindData(it)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClick
        )
    }

    object DataDifferentiator : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

}