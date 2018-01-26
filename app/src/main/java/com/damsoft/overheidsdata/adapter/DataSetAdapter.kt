package com.damsoft.overheidsdata.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.damsoft.overheidsdata.R
import com.damsoft.overheidsdata.adapter.viewholder.DataSetViewHolder
import com.damsoft.overheidsdata.apimodel.DataSet
import com.damsoft.overheidsdata.inflate

/**
 * Created by abhinav.sharma on 02/11/17.
 */
class DataSetAdapter(private val dataSets: List<DataSet>)
    : RecyclerView.Adapter<DataSetViewHolder>() {

    override fun onBindViewHolder(holder: DataSetViewHolder, position: Int) = holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataSetViewHolder = DataSetViewHolder(parent.inflate(R.layout.layout_dataset_single))


    override fun getItemCount(): Int = dataSets.size

    private fun getItem(position: Int): DataSet = dataSets[position]

}