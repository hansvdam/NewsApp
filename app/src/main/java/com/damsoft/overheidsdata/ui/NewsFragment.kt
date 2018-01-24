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
import com.damsoft.overheidsdata.R
import com.damsoft.overheidsdata.adapter.NewsArticleAdapter
import com.damsoft.overheidsdata.adapter.NewsSourceAdapter
import com.damsoft.overheidsdata.adapter.viewholder.ThemesAdapter
import com.damsoft.overheidsdata.api.Resource
import com.damsoft.overheidsdata.db.SourceEntity
import com.damsoft.overheidsdata.db.ThemeEntity
import com.damsoft.overheidsdata.ui.model.ArticlesResponse
import com.damsoft.overheidsdata.ui.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*

/**
 * Created by abhinav.sharma on 01/11/17.
 */
class NewsFragment : Fragment(), (SourceEntity) -> Unit {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var observerThemes: Observer<Resource<List<ThemeEntity>>>
    private lateinit var observerNewsSource: Observer<Resource<List<SourceEntity>>>
    private lateinit var observerNewsArticle: Observer<ArticlesResponse>
    private lateinit var themesAdapter: ThemesAdapter
    private lateinit var newsSourceAdapter: NewsSourceAdapter
    private lateinit var newsArticleAdapter: NewsArticleAdapter
    private val sourceList = ArrayList<SourceEntity>()
    private val themesList = ArrayList<ThemeEntity>()
    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = inflater?.inflate(R.layout.fragment_news, container, false)
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        progressDialog = ProgressDialog.show(activity, "News API", "Loading News Source from Web-Service")
        progressDialog.show()
        return view
    }

    private val themeListener: (ThemeEntity) -> Unit = {themeEntity -> println(themeEntity.name) }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        themesAdapter = ThemesAdapter(themeListener, themesList)
        newsSourceAdapter = NewsSourceAdapter(this, sourceList)
//        recyclerView.adapter = newsSourceAdapter
        recyclerView.adapter = themesAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        observerThemes = Observer { themes ->
            if (themes?.data != null && themes.data.isNotEmpty()) {
                progressDialog.dismiss()
                themesAdapter.updateDataSet(themes.data)
            }
        }

        observerNewsSource = Observer { newsSource ->
            if (newsSource?.data != null && newsSource.data.isNotEmpty()) {
                progressDialog.dismiss()
                newsSourceAdapter.updateDataSet(newsSource.data)
            }

        }

        observerNewsArticle = Observer { newsArticle ->
            if (newsArticle != null) {
                newsArticleAdapter = NewsArticleAdapter(newsArticle.articles!!)
                recyclerView.adapter = newsArticleAdapter
            }
        }


        newsViewModel.getThemes()
                .observe(this, observerThemes)

        newsViewModel.getNewsSource(null, null, null)
                .observe(this, observerNewsSource)
    }

    override fun invoke(source: SourceEntity) {
        newsViewModel.getNewsArticles(source.id!!, null)
                .observe(this, observerNewsArticle)
    }

    fun onBackPressed(): Boolean {
        return when {
            recyclerView.adapter is NewsArticleAdapter -> {
                recyclerView.adapter = newsSourceAdapter
                true
            }
            else -> false
        }
    }
}