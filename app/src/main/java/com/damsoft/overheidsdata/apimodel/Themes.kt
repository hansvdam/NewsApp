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
        @SerializedName("id") var id: String?, //abc-news-au
        @SerializedName("name") var name: String?, //ABC News (AU)
        @SerializedName("description") var description: String?, //Australia's most trusted source of local, national and world news. Comprehensive, independent, in-depth analysis, the latest business, sport, weather and more.
        @SerializedName("theme_facet") var theme_facet: String? //http://www.abc.net.au/news
)