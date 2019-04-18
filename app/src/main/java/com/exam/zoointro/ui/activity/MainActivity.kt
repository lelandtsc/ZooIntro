package com.exam.zoointro.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.exam.zoointro.R
import com.exam.zoointro.domain.entity.Area
import com.exam.zoointro.domain.entity.Plant
import com.exam.zoointro.ui.fragment.AreaDetailPage
import com.exam.zoointro.ui.fragment.AreaListPage
import com.exam.zoointro.ui.fragment.PlantDetailPage

class MainActivity : AppCompatActivity(), AreaListPage.Listener, AreaDetailPage.Listener {
    override fun sendPlantData(plant: Plant?) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left,
                R.anim.slide_in_from_left, R.anim.slide_out_to_right)
            .replace(R.id.page_container, PlantDetailPage.newInstance(plant), PlantDetailPage::class.java.name)
            .addToBackStack(null)
            .commit()
    }

    override fun sendAreaData(area: Area?) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left,
                R.anim.slide_in_from_left, R.anim.slide_out_to_right)
            .replace(R.id.page_container, AreaDetailPage.newInstance(area), AreaDetailPage::class.java.name)
            .addToBackStack(null)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.page_container, AreaListPage.newInstance(), AreaListPage::class.java.name)
                .commit()
        }
    }
}
