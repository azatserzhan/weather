package kz.home.common.models.sticker

import com.google.gson.annotations.SerializedName
import kz.home.common.models.sticker.CameraSticker

data class CameraStickerBody(
    @SerializedName("stickerList") val stickerList: List<CameraSticker>,
)