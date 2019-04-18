package com.exam.zoointro.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.exam.zoointro.R
import com.exam.zoointro.data.net.Rest
import com.exam.zoointro.domain.entity.Area
import com.exam.zoointro.domain.entity.Plant
import com.exam.zoointro.ui.item.PlantItem
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.page_area_detail.*
import kotlinx.android.synthetic.main.title_bar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AreaDetailPage: Fragment() {

    companion object {
        private const val TAG = "AreaDetailPage"

        fun newInstance(area: Area?): AreaDetailPage {
            val arguments = Bundle()
            arguments.putSerializable("E_Pic_URL", area?.e_pic_url)
            arguments.putSerializable("E_Name", area?.e_name)
            arguments.putSerializable("E_Info", area?.e_info)
            arguments.putSerializable("E_Memo", area?.e_memo)
            arguments.putSerializable("E_Category", area?.e_category)
            val areaDetailPage = AreaDetailPage()
            areaDetailPage.arguments = arguments
            return areaDetailPage
        }
    }

    interface Listener {
        fun sendPlantData(plant: Plant?)
    }

    private lateinit var mListener: Listener
    private var mPlantList: ArrayList<Plant>? = null
    private var mFilteredPlantList: List<Plant>? = null
    private var mAreaName: String? = null
    private val mAdapter = object: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return PlantItem.create(layoutInflater, parent, object: PlantItem.Listener{
                override fun onItemClick(pos: Int) {
                    mListener.sendPlantData(mFilteredPlantList?.get(pos))
                }
            })
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as PlantItem).bind(position, mFilteredPlantList?.get(position))
            val imageArea = holder.itemView?.findViewById<ImageView>(R.id.image_area)
            val textPlantName = holder.itemView?.findViewById<TextView>(R.id.text_plant_name)
            val textPlantAlsoKnown = holder.itemView?.findViewById<TextView>(R.id.text_plant_also_known)
            context?.let {
                imageArea?.let { Glide.with(it).load(mFilteredPlantList?.get(position)?.f_pic01_url).into(it) }
            }
            textPlantName?.text = mFilteredPlantList?.get(position)?.f_name_ch
            textPlantAlsoKnown?.text = mFilteredPlantList?.get(position)?.f_alsoKnown
        }

        override fun getItemCount(): Int {
            return mFilteredPlantList?.size ?: 0
        }

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Listener) {
            mListener = context
        } else {
            throw ClassCastException(context.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPlantList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.page_area_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val picUrl = arguments?.get("E_Pic_URL")
        val areaName = arguments?.get("E_Name").toString()
        val areaInfo = arguments?.get("E_Info").toString()
        val areaMemo = arguments?.get("E_Memo").toString()
        val areaCategory = arguments?.get("E_Category").toString()

        context?.let {
            image_area?.let { Glide.with(it).load(picUrl).into(it) }
        }
        text_area_info?.text = areaInfo
        text_area_memo?.text = areaMemo
        text_area_memo?.visibility = if (areaMemo.isBlank()) View.GONE else View.VISIBLE
        text_area_category?.text = areaCategory

        mAreaName = areaName
        title_bar?.init(0, areaName, true)
        button_left?.setOnClickListener { activity?.supportFragmentManager?.popBackStackImmediate() }
        val layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(recycler_plant_list?.context, layoutManager.orientation)
        recycler_plant_list?.layoutManager = layoutManager
        recycler_plant_list?.addItemDecoration(dividerItemDecoration)
        recycler_plant_list?.adapter = mAdapter

        layout_refresh.setOnRefreshListener { getPlantList() }

        mFilteredPlantList = mPlantList?.let { filterPlantList(it) }
        mAdapter.notifyDataSetChanged()
    }

    private fun getPlantList() {
        Rest.mApi?.getInfo(Rest.SCOPE, Rest.PLANT_RID)?.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                layout_refresh?.isRefreshing = false
                Log.d(TAG, "getPlantList() Failed, $t")
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.d(TAG, response.body().toString())
                layout_refresh?.isRefreshing = false
                val results = response.body()?.get(Rest.MEMBER_NAME_RESULT)?.asJsonObject?.get(Rest.MEMBER_NAME_RESULTS)
                val gson = Gson()
                mPlantList = gson.fromJson(results, object: TypeToken<List<Plant>>(){}.type)
                mFilteredPlantList = mPlantList?.let { filterPlantList(it) }
                mAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun filterPlantList(plantList: List<Plant>): ArrayList<Plant>? {
        val filteredPlantList: ArrayList<Plant>? = ArrayList()
        for (plant in plantList) {
            if (plant.f_location?.contains(mAreaName?.substring(0, (mAreaName?.length ?: 0) - 1).toString(),
                    false) == true) filteredPlantList?.add(plant)
        }
        return filteredPlantList
    }

}