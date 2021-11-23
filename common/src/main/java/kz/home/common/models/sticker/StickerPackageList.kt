package kz.home.common.models.sticker

import com.google.gson.annotations.SerializedName

data class StickerPackageList(
    @SerializedName("packageId") val packageId: Int,
    @SerializedName("packageName") val packageName: String,
    @SerializedName("packageImg") val packageImg: String,
    @SerializedName("packageCategory") val packageCategory: String,
    @SerializedName("packageKeywords") val packageKeywords: String,
    @SerializedName("packageAnimated") val packageAnimated: String,
    @SerializedName("isNew") val isNew: String,
    @SerializedName("artistName") val artistName: String,
    @SerializedName("language") val language: String,
    @SerializedName("isDownload") val isDownload: String,
    @SerializedName("isWish") val isWish: String,
    @SerializedName("packageDate") val packageDate: String,
    @SerializedName("price") val price: String,
    @SerializedName("stickers") val stickers: List<Sticker>
)