package kz.home.common.models.sticker

import com.google.gson.annotations.SerializedName

data class CameraSticker(
    @SerializedName("stickerId") val stickerId: Int,
    @SerializedName("keyword") val keyword: String,
    @SerializedName("stickerImg") val stickerImgUrl: String
)