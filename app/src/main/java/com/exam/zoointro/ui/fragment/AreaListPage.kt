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
import com.exam.zoointro.R
import com.exam.zoointro.data.net.Rest
import com.exam.zoointro.domain.entity.Area
import com.exam.zoointro.ui.item.AreaItem
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.page_area_list.*
import kotlinx.android.synthetic.main.title_bar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AreaListPage: Fragment() {

    companion object {
        private const val TAG = "AreaListPage"
        fun newInstance(): AreaListPage = AreaListPage()
    }

    interface Listener {
        fun sendAreaData(area: Area?)
    }

    private lateinit var mListener: Listener
    private var mAreaList: List<Area>? = null
    private val mAdapter = object: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return AreaItem.create(layoutInflater, parent, object: AreaItem.Listener{
                override fun onItemClick(pos: Int) {
                    mListener.sendAreaData(mAreaList?.get(pos))
                }
            })
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as AreaItem).bind(context, position, mAreaList?.get(position))
        }

        override fun getItemCount(): Int {
            return mAreaList?.size ?: 0
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAreaList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.page_area_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title_bar?.init(R.string.title_area_list, null,false)
        val layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(recycler_area_list?.context, layoutManager.orientation)
        recycler_area_list?.layoutManager = layoutManager
        recycler_area_list?.addItemDecoration(dividerItemDecoration)
        recycler_area_list?.adapter = mAdapter

        layout_refresh?.setOnRefreshListener { getAreaList() }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is Listener) {
            mListener = context
        } else {
            throw ClassCastException(context.toString())
        }
    }

    private fun getAreaList() {
        Rest.mApi?.getInfo(Rest.SCOPE, Rest.AREA_RID)?.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                layout_refresh?.isRefreshing = false
                Log.d(TAG, "getAreaList() Failed, $t")
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.d(TAG, response.body().toString())
                layout_refresh?.isRefreshing = false
                val results = response.body()?.get(Rest.MEMBER_NAME_RESULT)?.asJsonObject?.get(Rest.MEMBER_NAME_RESULTS)
                val gson = Gson()
                mAreaList = gson.fromJson(results, object: TypeToken<List<Area>>(){}.type)
                mAdapter.notifyDataSetChanged()
            }
        })
    }
}