package com.example.nationalityapi.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


//data class Countries(
//    val country: List<Country>,
//    val name: String
//)

data class Countries(
    @SerializedName("country") @Expose var country: List<Country>? = null,

    @SerializedName("name") @Expose var name: String? = null
)
