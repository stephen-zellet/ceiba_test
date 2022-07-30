package com.example.ceiba_test.feature.user.domain

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("phone")
    val phoneNumber:String,
    @SerializedName("email")
    val email:String
    )
