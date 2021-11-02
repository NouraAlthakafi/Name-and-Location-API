package com.example.nameandlocationapi

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIInterface {
    @Headers("Content-Type: application/json")
    @GET("/test/")
    fun showLocation(): retrofit2.Call<ArrayList<namesListItem?>>

    @Headers("Content-Type: application/json")
    @POST("/test/")
    fun addName(@Body newUbring: namesListItem): Call<namesListItem>
}