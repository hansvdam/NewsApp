package com.abhinav.newsapp.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.abhinav.newsapp.R
import com.abhinav.newsapp.adapter.viewholder.NewsSourceViewHolder
import com.abhinav.newsapp.db.SourceEntity
import com.abhinav.newsapp.db.ThemeEntity
import com.abhinav.newsapp.inflate
import com.abhinav.newsapp.ui.model.Source

/**
 * Created by abhinav.sharma on 01/11/17.
 */
class ThemesAdapter(private val listener: (ThemeEntity) -> Unit, private var sourceList: List<ThemeEntity>) : RecyclerView.Adapter<ThemeViewHolder>() {


    override fun getItemCount(): Int {
        return sourceList.size
    }

    private fun getItem(position: Int): ThemeEntity {
        return sourceList[position]
    }

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) =
            holder.bind(getItem(position), listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder =
            ThemeViewHolder(parent.inflate(R.layout.layout_theme_single))

    fun updateDataSet(data: List<ThemeEntity>){
        sourceList = data
        notifyDataSetChanged()
    }
}