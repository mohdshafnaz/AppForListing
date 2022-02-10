package com.example.appforlisting


import com.example.appforlisting.apiservices.ApiClient
import com.example.appforlisting.models.response.ResponseModel


class MainRepository() {


    private var mService = ApiClient.getInstance().api
/*    private var mService: ApiServices =
        RetrofitClient.getRetrofitInstance(Constants.URL, context)!!.create(
            ApiServices::class.java
        )*/


    suspend fun getInitiativeList(): ResponseModel? {
        return try {
            val response = mService.getNYList()
            response
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


}