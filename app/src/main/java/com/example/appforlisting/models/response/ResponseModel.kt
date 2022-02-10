package com.example.appforlisting.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class ResponseModel : Serializable {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("copyright")
    @Expose
    var copyright: String? = null

    @SerializedName("num_results")
    @Expose
    var numResults: Int? = null

    @SerializedName("results")
    @Expose
    var results: List<ResultList>? = null
}