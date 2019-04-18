package com.exam.zoointro.ui.item

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.exam.zoointro.R
import com.exam.zoointro.domain.entity.Area
import kotlinx.android.extensions.LayoutContainer

class AreaItem(override val containerView: View?,
                private val mListener: Listener?): RecyclerView.ViewHolder(containerView), LayoutContainer {

    companion object {
        fun create(inflater: LayoutInflater?, parent: ViewGroup?, listener: Listener?): AreaItem {
            val itemLayout = inflater?.inflate(R.layout.item_area, parent, false) as? ViewGroup
            return AreaItem(itemLayout, listener)
        }
    }


    interface Listener {
        fun onItemClick(pos: Int)
    }

    init {
        containerView?.setOnClickListener { v ->
            (v.tag as? Int)?.let { pos -> mListener?.onItemClick(pos) }
        }
    }

    fun bind(position: Int, area: Area?) {
        containerView?.tag = position
    }

}