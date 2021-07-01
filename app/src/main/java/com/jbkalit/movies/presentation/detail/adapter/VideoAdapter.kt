package com.jbkalit.movies.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jbkalit.domain.model.Video
import com.jbkalit.movies.databinding.ViewVideoItemBinding
import com.jbkalit.movies.util.Helper.buildYouTubeThumbnailURL

class VideoAdapter(
    private var videoList: MutableList<Video>,
    private val listener: OnVideoClickedListener
) : ListAdapter<Video, RecyclerView.ViewHolder>(VideoResponseItemDiffCallback()) {

    override fun getItemCount() = videoList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ViewVideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(videoList[position])
    }

    inner class ViewHolder(private val binding: ViewVideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(video: Video) {
            with(binding) {
                Glide.with(this@ViewHolder.itemView)
                    .asBitmap()
                    .load(video.key?.let { buildYouTubeThumbnailURL(it) })
                    .into(videoPoster)

                videoPlay.setOnClickListener {
                    video.key?.let { it1 -> listener.onVideoClick(it1) }
                }
            }
        }
    }

    class VideoResponseItemDiffCallback : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem == newItem
        }
    }

    fun addToList(videoList: MutableList<Video>) {
        this.videoList.addAll(videoList)
        notifyDataSetChanged()
    }

    fun clearList() {
        this.videoList.clear()
        notifyDataSetChanged()
    }

    interface OnVideoClickedListener {
        fun onVideoClick(key: String)
    }
}
