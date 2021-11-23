package kz.home.common.content

import android.view.View
import kz.home.common.R
import kz.home.common.adapters.ContentType
import kz.home.common.content.holders.CityViewHolder

enum class ItemContentType : ContentType {
    CITY_ITEM {
        override fun type(): Int = ordinal
        override fun getLayout(): Int = R.layout.item_video
        override fun createHolder(view: View) = CityViewHolder(view)
    }
}
