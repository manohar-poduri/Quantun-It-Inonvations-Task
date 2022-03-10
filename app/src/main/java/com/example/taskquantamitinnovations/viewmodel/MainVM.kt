package com.example.taskquantamitinnovations.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskquantamitinnovations.MainActivity
import com.example.taskquantamitinnovations.localstorage.PublishedRepository
import com.example.taskquantamitinnovations.services.APIResponse
import com.example.taskquantamitinnovations.services.PublishedAPI
import com.example.taskquantamitinnovations.services.RetroFitCallBack
import com.example.taskquantamitinnovations.services.PublishedModel

class MainVM(application: Application) : AndroidViewModel(application) {

    var model: MutableLiveData<MutableList<PublishedModel>> = MutableLiveData()

    fun getData(context:Context) {
        val activity = context as? MainActivity
        activity?.activityLoader(true)
        PublishedAPI().published(object : RetroFitCallBack {
            override fun responseListener(response: Any?, error: String?) {
                activity?.activityLoader(false)
                if (error != null && response == null) {
                    Toast.makeText(getApplication(), "$error", Toast.LENGTH_SHORT).show()
                } else {
                    val apiResponse = response as? APIResponse<*>
                    if(apiResponse?.status == "ok") {
                        val data = (apiResponse.articles as? MutableList<*>)?.filterIsInstance<PublishedModel>()?.toMutableList()
                        model.value = data 
                    } else {
                        Toast.makeText(getApplication(), "${apiResponse?.status}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}