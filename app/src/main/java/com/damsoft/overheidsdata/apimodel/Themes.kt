package com.damsoft.overheidsdata.apimodel

import com.google.gson.annotations.SerializedName

/**
 * Created by abhinav.sharma on 31/10/17.
 */

data class ThemeResponse(
        @SerializedName("status") var status: String?, //ok
        @SerializedName("themes") var sources: List<ThemeItem>
)

data class ThemeItem(
        @SerializedName("id") var id: String?,
        @SerializedName("name") var name: String?,
        @SerializedName("description") var description: String?,
        @SerializedName("theme_facet") var theme_facet: String?
)