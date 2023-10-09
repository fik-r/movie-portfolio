package com.example.myapplication.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.model.Movie
import com.example.myapplication.BuildConfig
import com.example.myapplication.databinding.ItemMovieBinding

class MovieAdapter(private val onItemClick: (userId: String) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    val listUser: MutableList<Movie> = mutableListOf()

    class MovieViewHolder(
        private val binding: ItemMovieBinding,
        private val onItemClick: (userId: String) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick
        )
    }

    override fun getItemCount(): Int = listUser.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindData(listUser[position])
    }


}