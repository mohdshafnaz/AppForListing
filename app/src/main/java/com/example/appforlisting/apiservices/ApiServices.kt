package com.example.appforlisting.apiservices


import com.example.appforlisting.models.response.ResponseModel
import com.example.appforlisting.utils.Constants
import retrofit2.http.GET

interface ApiServices {

    @GET(Constants.URL)
    suspend fun getNYList(
    ): ResponseModel


}
