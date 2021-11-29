package com.anushmp.takeawaymovieassignment.datamodels.remote

import com.google.gson.annotations.SerializedName

data class Genres (

    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String
)