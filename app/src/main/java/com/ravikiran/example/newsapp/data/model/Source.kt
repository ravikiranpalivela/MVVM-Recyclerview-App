package com.ravikiran.example.newsapp.data.model


import com.google.gson.annotations.SerializedName

data class Source(

    // save the id and name in room
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)