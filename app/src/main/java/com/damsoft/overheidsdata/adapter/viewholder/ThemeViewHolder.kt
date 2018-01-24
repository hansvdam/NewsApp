package com.damsoft.overheidsdata.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.damsoft.overheidsdata.db.ThemeEntity
import kotlinx.android.synthetic.main.layout_theme_single.view.*

/**
 * Created by abhinav.sharma on 01/11/17.
 */
class ThemeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(theme: ThemeEntity, listener: (ThemeEntity) -> Unit) = with(itemView) {
        tv_source_name.text = theme.name
        tv_source_description.text = theme.description
        tv_category.text = "dummy text"
        itemView.setOnClickListener { listener(theme) }
    }
}