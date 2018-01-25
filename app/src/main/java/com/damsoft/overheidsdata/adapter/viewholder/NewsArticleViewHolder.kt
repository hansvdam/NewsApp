package com.damsoft.overheidsdata.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.damsoft.overheidsdata.load
import com.damsoft.overheidsdata.model.packages.DataSet
import kotlinx.android.synthetic.main.layout_news_article_single.view.*

/**
 * Created by abhinav.sharma on 02/11/17.
 */
class NewsArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(article: DataSet) = with(itemView){
        organization.text = article.organization.name
        dataset_notes.text = article.notes
        dataset_name.text = article.name
        iv_article_image.load("https://avatars2.githubusercontent.com/u/2060172?s=460&v=4") { requestCreator -> requestCreator.fit().centerCrop() }
//        Picasso.with(itemView.context).load(article.urlToImage).fit().centerCrop().into(iv_article_image)
    }
}