package com.damsoft.overheidsdata.ui

import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.damsoft.overheidsdata.R
import com.damsoft.overheidsdata.adapter.DataSetAdapter
import com.damsoft.overheidsdata.adapter.ThemesAdapter
import com.damsoft.overheidsdata.api.Resource
import com.damsoft.overheidsdata.api.Status
import com.damsoft.overheidsdata.apimodel.DataSets
import com.damsoft.overheidsdata.db.ThemeEntity
import com.damsoft.overheidsdata.ui.viewmodel.DataViewModel
import kotlinx.android.synthetic.main.fragment_news.*

/**
 * Created by abhinav.sharma on 01/11/17.
 */
class NewsFragment : Fragment() {

    private lateinit var dataViewModel: DataViewModel
    private lateinit var observerThemes: Observer<Resource<List<ThemeEntity>>>
    private lateinit var observerDataSets: Observer<DataSets>
    private lateinit var themesAdapter: ThemesAdapter
    private lateinit var dataSetAdapter: DataSetAdapter
    private val themesList = ArrayList<ThemeEntity>()
    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = inflater?.inflate(R.layout.fragment_news, container, false)
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel::class.java)
        progressDialog = ProgressDialog.show(activity, "News API", "Loading News Source from Web-Service")
        progressDialog.show()
        return view
    }

    private val themeListener: (ThemeEntity) -> Unit = { themeEntity ->
        dataViewModel.getDataSets(themeEntity.theme_facet!!, null)
                .observe(this, observerDataSets)
        println(themeEntity.name)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        themesAdapter = ThemesAdapter(themeListener, themesList)
        recyclerView.adapter = themesAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        observerThemes = Observer { themesResponse ->
            if (themesResponse?.data != null && themesResponse.data.isNotEmpty()) {
                progressDialog.dismiss()
                themesAdapter.updateDataSet(themesResponse.data)
            }
            // not the best way to show that fetching data failed, but the original code showed nothing when
            // DB-data is outdated, but re-fetching fails.
            if (themesResponse?.status == Status.ERROR) {
                Toast.makeText(this.activity, themesResponse.message, Toast.LENGTH_LONG).show()
            }
        }

        observerDataSets = Observer { dataSets ->
            if (dataSets != null) {
                dataSetAdapter = DataSetAdapter(dataSets.result.results!!)
                recyclerView.adapter = dataSetAdapter
            }
        }


        dataViewModel.getThemes()
                .observe(this, observerThemes)

    }

    fun onBackPressed(): Boolean {
        return when {
            recyclerView.adapter is DataSetAdapter -> {
                recyclerView.adapter = themesAdapter
                true
            }
            else -> false
        }
    }
}