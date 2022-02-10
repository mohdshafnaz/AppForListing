package com.example.appforlisting.models.response

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable




class MediaMetadatum : Serializable {
    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("format")
    @Expose
    var format: String? = null

    @SerializedName("height")
    @Expose
    var height: Int? = null

    @SerializedName("width")
    @Expose
    var width: Int? = null
}