package com.ugrcaan.learningapi.service

import com.ugrcaan.learningapi.model.CurrencyModel
import retrofit2.Call
import retrofit2.http.GET

interface CurrencyAPI {
    //API: https://raw.githubusercontent.com/ugurcan-sevinc/Curr-JSON-Dataset/main/CurrencyJSONDataSet.json

    @GET("ugurcan-sevinc/Curr-JSON-Dataset/main/CurrencyJSONDataSet.json")
    fun getData(): Call<List<CurrencyModel>>
}