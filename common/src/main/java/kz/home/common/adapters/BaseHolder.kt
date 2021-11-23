package kz.home.common.adapters

import android.content.Context
import android.os.Parcelable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class BaseHolder<C : ContentType, T: BaseContentItem<C>>(
        override val containerView: View): RecyclerView.ViewHolder(containerView),
        LayoutContainer {

    open var action1: ((payload: Parcelable?) -> Unit)? = null
    open var action2: ((payload: Parcelable?) -> Unit)? = null
    open var action3: ((payload: Parcelable?) -> Unit)? = null
    private var homeItem: BaseContentItem<*>? = null
    val context: Context
        get() = containerView.context

    fun bind(item: BaseContentItem<*>,
             callBack1: ((item: BaseContentItem<C>, payload: Parcelable?) -> Unit)? = null,
             callBack2: ((item: BaseContentItem<C>, payload: Parcelable?) -> Unit)? = null,
             callBack3: ((item: BaseContentItem<C>, payload: Parcelable?) -> Unit)? = null) {
        homeItem = item
        action1 = { callBack1?.invoke(item as BaseContentItem<C>, it)}
        action2 = { callBack2?.invoke(item as BaseContentItem<C>, it)}
        action3 = { callBack3?.invoke(item as BaseContentItem<C>, it)}
        (homeItem as? T)?.let { bindItem(it) }
    }

    @Suppress("UnnecessaryParentheses")
    fun getItem() = (homeItem as? T)
    open fun getNestedRecyclerView(): RecyclerView? = null
    abstract fun bindItem(item: T)
    fun updateHolder(item: BaseContentItem<*>, keys: Set<String>) {
        homeItem = item
        keys.forEach {
            update(it, item as T)
        }
    }
    open fun update(key: String, item: T) { }
    open fun onViewRecycled() { }
    open fun onViewAttachedToWindow() { }
    open fun onViewDetachedFromWindow() { }
}