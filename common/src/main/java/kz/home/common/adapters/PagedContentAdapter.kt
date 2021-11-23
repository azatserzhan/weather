package kz.home.common.adapters

import android.os.Parcelable
import android.view.ViewGroup
import androidx.paging.PagedListAdapter

class PagedContentAdapter<T : ContentType>(
        private val typeSet : Array<T>
) : PagedListAdapter<BaseContentItem<T>, BaseHolder<T, BaseContentItem<T>>>(
        BaseContentItem.DiffItem()
) {

    var action1: ((item: BaseContentItem<T>, payload: Parcelable?) -> Unit)? = null
    var action2: ((item: BaseContentItem<T>, payload: Parcelable?) -> Unit)? = null
    var action3: ((item: BaseContentItem<T>, payload: Parcelable?) -> Unit)? = null
    private val delegateAdapter = ContentAdapter(typeSet)

    override fun getItemViewType(position: Int) = getItem(position)?.type?.type() ?: -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        delegateAdapter.onCreateViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: BaseHolder<T, BaseContentItem<T>>, position: Int) {
        getItem(position)?.let {
            holder.bind(it, action1, action2, action3)
        }
    }

    @Suppress("UnnecessaryParentheses")
    override fun onBindViewHolder(
        holder: BaseHolder<T, BaseContentItem<T>>, position: Int,
        payloads: MutableList<Any>
    ) = (payloads.firstOrNull()).let { status ->
        if (status == null || status !is Set<*>) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            getItem(position)?.let {
                holder.updateHolder(it, status as Set<String>)
            } ?: Unit
        }
    }

    override fun onViewRecycled(holder: BaseHolder<T, BaseContentItem<T>>) =
            holder.onViewRecycled()

    override fun onViewAttachedToWindow(holder: BaseHolder<T, BaseContentItem<T>>) =
            holder.onViewAttachedToWindow()

    override fun onViewDetachedFromWindow(holder: BaseHolder<T, BaseContentItem<T>>) =
            holder.onViewDetachedFromWindow()
}