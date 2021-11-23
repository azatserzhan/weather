package kz.home.common.content.items

import kz.home.common.adapters.BaseContentItem
import kz.home.common.content.ItemContentType
import kz.home.common.models.PagingItem

data class CityItem(
    val countryName: String,
    val cityName: String,
    val onItemClickListener: (() -> Unit)? = null,
) : BaseContentItem<ItemContentType>(countryName) {
    override val type: ItemContentType = ItemContentType.CITY_ITEM
}
