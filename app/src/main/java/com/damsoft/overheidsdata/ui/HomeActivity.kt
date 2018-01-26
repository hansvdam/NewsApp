package com.damsoft.overheidsdata.ui

import android.os.Bundle
import com.damsoft.overheidsdata.R

/**
 * Created by abhinav.sharma on 31/10/17.
 */
class HomeActivity : BaseActivity() {

    private var dataListFragment: DataListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        showSourceFragment()
    }

    private fun showSourceFragment() {
        dataListFragment = DataListFragment()
        addFragment(dataListFragment!!, R.id.container, "DataListFragment")
    }

    override fun onBackPressed() {
        if (!dataListFragment?.onBackPressed()!!)
            super.onBackPressed()
    }
}