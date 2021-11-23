package kz.home.common.models.sticker

import com.google.gson.annotations.SerializedName

data class StickerHeader(
    @SerializedName("code") val code: String,
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
)