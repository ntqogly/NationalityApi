package com.example.nationalityapi.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


//data class Country(
//    val country_id: String,
//    val probability: Double
//)

data class Country(
    @SerializedName("country_id") @Expose var countryId: String? = null,

    @SerializedName("probability") @Expose var probability: Double? = null
)