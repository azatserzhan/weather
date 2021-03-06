package kz.home.common.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView

interface ContentType {
    fun type() : Int
    fun getLayout() : Int
    fun createHolder(view: View) : RecyclerView.ViewHolder
}