package com.jbkalit.movies.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jbkalit.domain.model.Review
import com.jbkalit.movies.databinding.ViewReviewItemBinding

class ReviewAdapter(private var reviewList: MutableList<Review>) :
    ListAdapter<Review, RecyclerView.ViewHolder>(ReviewResponseItemDiffCallback()) {

    override fun getItemCount() = reviewList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ViewReviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(reviewList[position])
    }

    inner class ViewHolder(private val binding: ViewReviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(review: Review) {
            with(binding) {
                author.text = review.author
                content.text = review.content
            }
        }
    }

    class ReviewResponseItemDiffCallback : DiffUtil.ItemCallback<Review>() {
        override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem == newItem
        }
    }

    fun addToList(reviewList: MutableList<Review>) {
        this.reviewList.addAll(reviewList)
        notifyDataSetChanged()
    }

    fun clearList() {
        this.reviewList.clear()
        notifyDataSetChanged()
    }

}
