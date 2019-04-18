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

        fun newInstance(plant: Plant?): PlantDetailPage {
            val arguments = Bundle()
            arguments.putSerializable("F_Pic01_URL", plant?.f_pic01_url)
            arguments.putSerializable("F_Name_Ch", plant?.f_name_ch)
            arguments.putSerializable("F_Name_En", plant?.f_name_en)
            arguments.putSerializable("F_AlsoKnown", plant?.f_alsoKnown)
            arguments.putSerializable("F_Brief", plant?.f_brief)
            arguments.putSerializable("F_Feature", plant?.f_feature)
            arguments.putSerializable("F_Function&Application", plant?.f_functionApplication)
            arguments.putSerializable("F_Update", plant?.f_update)
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

        val picUrl = arguments?.get("F_Pic01_URL")
        val plantChName = arguments?.get("F_Name_Ch").toString()
        val plantEnName = arguments?.get("F_Name_En").toString()
        val plantAlsoKnown = arguments?.get("F_AlsoKnown").toString()
        val plantBrief = arguments?.get("F_Brief").toString()
        val plantFeature = arguments?.get("F_Feature").toString()
        val plantFuntion = arguments?.get("F_Function&Application").toString()
        val plantLastUpdate = arguments?.get("F_Update").toString()

        context?.let {
            image_plant?.let { Glide.with(it).load(picUrl).into(it) }
        }
        text_plant_ChName?.text = plantChName
        text_plant_EnName?.text = plantEnName
        text_plant_also_known?.text = plantAlsoKnown
        text_plant_brief?.text = plantBrief
        text_plant_feature?.text = plantFeature
        text_plant_function?.text = plantFuntion
        text_plant_last_update?.text = plantLastUpdate

        title_bar?.init(0, plantChName, true)
        button_left?.setOnClickListener { activity?.supportFragmentManager?.popBackStackImmediate() }
    }

}