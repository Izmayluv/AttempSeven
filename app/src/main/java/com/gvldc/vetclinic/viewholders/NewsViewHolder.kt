package com.gvldc.vetclinic.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.models.RVDataModels
import com.gvldc.vetclinic.databinding.ItemNewsBinding
import com.gvldc.vetclinic.utils.MyUtils

class NewsViewHolder(
    private val binding: ItemNewsBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(news: RVDataModels.ItemNews){
        binding.apply {
            textViewTitle.text = news.title
            textViewNews.text = news.news

            MyUtils.loadImageFromUrlFit(
                news.imageUrl,
                imageView
            )
        }
    }
}