package com.damsoft.overheidsdata.apimodel.packages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataSets {

    @SerializedName("help")
    @Expose
    public String help;
    @SerializedName("success")
    @Expose
    public Boolean success;
    @SerializedName("result")
    @Expose
    public Result result;

}
