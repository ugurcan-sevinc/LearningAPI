package com.ugrcaan.learningapi.model

data class CurrencyModel(
    val id: String,
    val name: String,
    val logo_url: String,
    val price: Double,
    val rank: Int
    )