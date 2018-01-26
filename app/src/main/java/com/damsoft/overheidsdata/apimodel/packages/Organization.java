package com.damsoft.overheidsdata.apimodel.packages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Organization {

    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("created")
    @Expose
    public String created;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("is_organization")
    @Expose
    public Boolean isOrganization;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("image_url")
    @Expose
    public String imageUrl;
    @SerializedName("revision_id")
    @Expose
    public String revisionId;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("approval_status")
    @Expose
    public String approvalStatus;

}
