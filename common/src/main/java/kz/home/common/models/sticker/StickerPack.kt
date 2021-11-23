package kz.home.common.models.sticker

import com.google.gson.annotations.SerializedName

data class StickerPack(
    @SerializedName("header") val stickerHeader: StickerHeader,
    @SerializedName("body") val stickerBody: StickerBody
)