package kz.home.common.content.holders

import android.view.View
import kotlinx.android.synthetic.main.item_video.view.cityName
import kotlinx.android.synthetic.main.item_video.view.countryName
import kz.home.common.adapters.BaseHolder
import kz.home.common.content.ItemContentType
import kz.home.common.content.items.CityItem
import kz.home.common.util.setThrottleOnClickListener

class CityViewHolder(containerView: View) : BaseHolder<ItemContentType, CityItem>(containerView) {

    init {
        itemView.setThrottleOnClickListener {
            getItem()?.onItemClickListener?.invoke()
        }
    }

    override fun bindItem(item: CityItem) {
        itemView.cityName.text = item.cityName
        itemView.countryName.text = item.countryName
    }
}
