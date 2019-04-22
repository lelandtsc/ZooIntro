package com.exam.zoointro.ui.item

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.exam.zoointro.R
import com.exam.zoointro.domain.entity.Area
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_area.*

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

    fun bind(context: Context?, position: Int, area: Area?) {
        containerView?.tag = position
        text_area_title?.text = area?.e_name
        text_area_info?.text = area?.e_info
        text_area_memo?.text = area?.e_memo
        context?.let {
            image_area?.let { Glide.with(it).load(area?.e_pic_url).into(it) }
        }
    }

}