package kz.home.common.models.sticker

import com.google.gson.annotations.SerializedName

data class Sticker(
    @SerializedName("stickerId") val stickerId: Int,
    @SerializedName("packageId") val packageId: Int? = null,
    @SerializedName("stickerImg") val stickerImgUrl: String,
    @SerializedName("favoriteYN") val favoriteYN: String? = null
)