package com.exam.zoointro.ui.item

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.exam.zoointro.R
import com.exam.zoointro.domain.entity.Plant
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_plant.*

class PlantItem(override val containerView: View?,
                private val mListener: Listener?): RecyclerView.ViewHolder(containerView), LayoutContainer {

    companion object {
        fun create(inflater: LayoutInflater?, parent: ViewGroup?, listener: Listener?): PlantItem {
            val itemLayout = inflater?.inflate(R.layout.item_plant, parent, false) as? ViewGroup
            return PlantItem(itemLayout, listener)
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

    fun bind(context: Context?, position: Int, plant: Plant?) {
        containerView?.tag = position
        text_plant_name?.text = plant?.f_name_ch
        text_plant_also_known?.text = plant?.f_alsoKnown
        context?.let {
            image_area?.let { Glide.with(it).load(plant?.f_pic01_url).into(it) }
        }
    }

}