package com.damsoft.overheidsdata.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.damsoft.overheidsdata.ui.NewsConstants

/**
 * Created by abhinav.sharma on 04/11/17.
 */

@Entity(tableName = NewsConstants.T_THEME)
class ThemeEntity(
        @PrimaryKey()
        var id: String? = "",
        var name: String? = "",
        var description: String? = "",
        var theme_facet: String? = "" // main category used by datasets in the (Dutch) Government)
)