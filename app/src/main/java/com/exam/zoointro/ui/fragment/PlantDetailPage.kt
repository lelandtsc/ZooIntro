package com.exam.zoointro.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.exam.zoointro.R
import com.exam.zoointro.domain.entity.Plant
import kotlinx.android.synthetic.main.page_plant_detail.*
import kotlinx.android.synthetic.main.title_bar.*

class PlantDetailPage: Fragment() {

    companion object {
        private const val TAG = "PlantDetailPage"
        private const val KEY_PLANT = "plant"

        fun newInstance(plant: Plant?): PlantDetailPage {
            val arguments = Bundle()
            arguments.putParcelable(KEY_PLANT, plant)
            val plantDetailPage = PlantDetailPage()
            plantDetailPage.arguments = arguments
            return plantDetailPage
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.page_plant_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val plant = arguments?.getParcelable<Plant>(KEY_PLANT)

        context?.let {
            image_plant?.let { Glide.with(it).load(plant?.f_pic01_url).into(it) }
        }
        text_plant_ChName?.text = plant?.f_name_ch
        text_plant_EnName?.text = plant?.f_name_en
        text_plant_also_known?.text = plant?.f_alsoKnown
        text_plant_brief?.text = plant?.f_brief
        text_plant_feature?.text = plant?.f_feature
        text_plant_function?.text = plant?.f_functionApplication
        text_plant_last_update?.text = plant?.f_update

        title_bar?.init(0, plant?.f_name_ch, true)
        button_left?.setOnClickListener { activity?.supportFragmentManager?.popBackStackImmediate() }
    }

}