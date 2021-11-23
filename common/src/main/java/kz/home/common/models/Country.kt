package kz.home.common.models

import com.google.gson.annotations.SerializedName

data class Country(

    @SerializedName("_id") var Id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("coord") var coordinate: Coordinate? = Coordinate()

)