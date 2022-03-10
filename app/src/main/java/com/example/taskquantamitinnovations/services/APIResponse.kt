package com.example.taskquantamitinnovations.services

class APIResponse<T> {
    var status: String? = null
    var totalResults: String? = null
    var articles: T? = null
}