package com.example.taskquantamitinnovations.services

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET("everything?q=tesla&from=2022-03-05&sortBy=publishedAt&apiKey=90268c49fe894e61921dea8dabde5c9b")
    fun published(): Call<APIResponse<MutableList<PublishedModel>>>
}