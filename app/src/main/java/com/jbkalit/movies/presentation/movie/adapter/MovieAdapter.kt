package com.jbkalit.movies.presentation.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jbkalit.data.util.BASE_URL_POSTER_SIZE_500
import com.jbkalit.domain.model.Movie
import com.jbkalit.movies.databinding.ViewMovieItemBinding

class MovieAdapter(
    private var movieList: MutableList<Movie>,
    private val listener: OnMovieClickedListener
) : ListAdapter<Movie, RecyclerView.ViewHolder>(MovieResponseItemDiffCallback()) {

    override fun getItemCount() = movieList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ViewMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(movieList[position])
    }

    inner class ViewHolder(private val binding: ViewMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                Glide.with(this@ViewHolder.itemView)
                    .asBitmap()
                    .load(BASE_URL_POSTER_SIZE_500 + movie.backdropPath)
                    .into(poster)
                title.text = movie.originalTitle

                poster.setOnClickListener {
                    listener.onMovieClick(movie.id)
                }

            }
        }
    }

    class MovieResponseItemDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    fun addToList(movieList: MutableList<Movie>) {
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }

    fun clearList() {
        this.movieList.clear()
        notifyDataSetChanged()
    }

    interface OnMovieClickedListener {
        fun onMovieClick(movieId: Int)
    }
}
