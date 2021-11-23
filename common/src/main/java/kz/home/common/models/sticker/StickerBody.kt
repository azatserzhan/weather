package kz.home.common.models.sticker

import com.google.gson.annotations.SerializedName

data class StickerBody(
    @SerializedName("packageList") val stickerPackageList: List<StickerPackageList>,
    @SerializedName("pageMap") val stickerPageMap: StickerPageMap
)