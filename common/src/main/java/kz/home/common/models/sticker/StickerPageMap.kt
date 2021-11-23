package kz.home.common.models.sticker

import com.google.gson.annotations.SerializedName

data class StickerPageMap(
    @SerializedName("pageNumber") val pageNumber: Int,
    @SerializedName("onePageCountRow") val onePageCountRow: Int,
    @SerializedName("totalCount") val totalCount: Int,
    @SerializedName("pageCount") val pageCount: Int,
    @SerializedName("groupCount") val groupCount: Int,
    @SerializedName("groupNumber") val groupNumber: Int,
    @SerializedName("pageGroupCount") val pageGroupCount: Int,
    @SerializedName("startPage") val startPage: Int,
    @SerializedName("endPage") val endPage: Int,
    @SerializedName("startRow") val startRow: Int,
    @SerializedName("endRow") val endRow: Int,
    @SerializedName("modNum") val modNum: Int,
    @SerializedName("listStartNumber") val listStartNumber: Int
)