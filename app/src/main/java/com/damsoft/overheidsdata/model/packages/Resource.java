
package com.damsoft.overheidsdata.model.packages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Resource {

    @SerializedName("cache_last_updated")
    @Expose
    public Object cacheLastUpdated;
    @SerializedName("bytesize")
    @Expose
    public String bytesize;
    @SerializedName("package_id")
    @Expose
    public String packageId;
    @SerializedName("webstore_last_updated")
    @Expose
    public Object webstoreLastUpdated;
    @SerializedName("datastore_active")
    @Expose
    public Boolean datastoreActive;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("size")
    @Expose
    public Object size;
    @SerializedName("download_url")
    @Expose
    public String downloadUrl;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("hash")
    @Expose
    public String hash;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("format")
    @Expose
    public String format;
    @SerializedName("mimetype_inner")
    @Expose
    public Object mimetypeInner;
    @SerializedName("url_type")
    @Expose
    public Object urlType;
    @SerializedName("link_last_checked_date")
    @Expose
    public String linkLastCheckedDate;
    @SerializedName("mimetype")
    @Expose
    public Object mimetype;
    @SerializedName("cache_url")
    @Expose
    public Object cacheUrl;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("link_status")
    @Expose
    public Boolean linkStatus;
    @SerializedName("created")
    @Expose
    public String created;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("webstore_url")
    @Expose
    public Object webstoreUrl;
    @SerializedName("last_modified")
    @Expose
    public Object lastModified;
    @SerializedName("position")
    @Expose
    public Integer position;
    @SerializedName("revision_id")
    @Expose
    public String revisionId;
    @SerializedName("resource_type")
    @Expose
    public Object resourceType;
    @SerializedName("protocol")
    @Expose
    public String protocol;

}
