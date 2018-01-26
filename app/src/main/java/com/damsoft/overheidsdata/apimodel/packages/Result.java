package com.damsoft.overheidsdata.apimodel.packages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("sort")
    @Expose
    public String sort;
    @SerializedName("facets")
    @Expose
    public Facets facets;
    @SerializedName("results")
    @Expose
    public List<DataSet> results = null;
    @SerializedName("search_facets")
    @Expose
    public SearchFacets searchFacets;

}
