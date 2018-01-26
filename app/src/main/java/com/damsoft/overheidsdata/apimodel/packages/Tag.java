package com.damsoft.overheidsdata.apimodel.packages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tag {

    @SerializedName("vocabulary_id")
    @Expose
    public Object vocabularyId;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("display_name")
    @Expose
    public String displayName;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;

}
