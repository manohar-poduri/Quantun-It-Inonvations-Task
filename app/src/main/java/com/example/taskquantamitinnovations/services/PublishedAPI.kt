package com.example.taskquantamitinnovations.services

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PublishedAPI {

    fun published(retroFitCallBack: RetroFitCallBack){
        val apiClient  = APIClient().getInstance().create(APIInterface::class.java)
        val call: Call<APIResponse<MutableList<PublishedModel>>> = apiClient.published()
        call.enqueue(object : Callback<APIResponse<MutableList<PublishedModel>>>{
            override fun onResponse(
                call: Call<APIResponse<MutableList<PublishedModel>>>,
                response: Response<APIResponse<MutableList<PublishedModel>>>
            ) {
                retroFitCallBack.responseListener(response.body())
            }

            override fun onFailure(
                call: Call<APIResponse<MutableList<PublishedModel>>>,
                t: Throwable
            ) {
                retroFitCallBack.responseListener(null,t.message)
            }
        })
    }
}