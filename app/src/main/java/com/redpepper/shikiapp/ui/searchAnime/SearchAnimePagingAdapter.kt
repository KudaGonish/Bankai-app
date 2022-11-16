package com.redpepper.shikiapp.ui.searchAnime

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.redpepper.shikiapp.data.remote.models.AnimeEntityItem
import com.redpepper.shikiapp.databinding.SearchAnimeCardItemBinding
import com.redpepper.shikiapp.utils.Constants

class SearchAnimePagingAdapter() :
    PagingDataAdapter<AnimeEntityItem, SearchAnimePagingAdapter.CardViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<AnimeEntityItem>() {
            override fun areItemsTheSame(
                oldItem: AnimeEntityItem,
                newItem: AnimeEntityItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: AnimeEntityItem,
                newItem: AnimeEntityItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class CardViewHolder(private val viewDataBinding: SearchAnimeCardItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind(item: AnimeEntityItem) = with(viewDataBinding) {
            animeName.text = item.rusName
            animeType.text = item.kind
            rating.text = item.ratingScore
            Glide.with(animeImage)
                .load(Constants.BASE_URL_FOR_IMAGE + item.image.preview)
                .into(animeImage)
        }
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
       getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CardViewHolder(
        viewDataBinding = SearchAnimeCardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )
}