package com.damsoft.overheidsdata.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.damsoft.overheidsdata.R
import com.damsoft.overheidsdata.adapter.viewholder.NewsArticleViewHolder
import com.damsoft.overheidsdata.inflate
import com.damsoft.overheidsdata.model.packages.DataSet

/**
 * Created by abhinav.sharma on 02/11/17.
 */
class NewsArticleAdapter(private val articles: List<DataSet>)
    : RecyclerView.Adapter<NewsArticleViewHolder>() {

    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int)
            = holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsArticleViewHolder
            = NewsArticleViewHolder(parent.inflate(R.layout.layout_news_article_single))


    override fun getItemCount(): Int = articles.size

    private fun getItem(position: Int): DataSet = articles[position]

}