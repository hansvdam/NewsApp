package com.damsoft.overheidsdata.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.damsoft.overheidsdata.R
import com.damsoft.overheidsdata.adapter.viewholder.ThemeViewHolder
import com.damsoft.overheidsdata.db.ThemeEntity
import com.damsoft.overheidsdata.inflate

/**
 * Created by abhinav.sharma on 01/11/17.
 */
class ThemesAdapter(private val listener: (ThemeEntity) -> Unit, private var themesList: List<ThemeEntity>) : RecyclerView.Adapter<ThemeViewHolder>() {


    override fun getItemCount(): Int {
        return themesList.size
    }

    private fun getItem(position: Int): ThemeEntity {
        return themesList[position]
    }

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) =
            holder.bind(getItem(position), listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder =
            ThemeViewHolder(parent.inflate(R.layout.layout_theme_single))

    fun updateDataSet(data: List<ThemeEntity>) {
        themesList = data
        notifyDataSetChanged()
    }
}