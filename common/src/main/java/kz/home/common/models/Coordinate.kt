package kz.home.common.models

import com.google.gson.annotations.SerializedName

data class Coordinate(

    @SerializedName("lon") var lon: Double? = null,
    @SerializedName("lat") var lat: Double? = null

)