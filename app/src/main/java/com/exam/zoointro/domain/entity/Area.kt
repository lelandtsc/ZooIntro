package com.exam.zoointro.domain.entity

import com.google.gson.annotations.SerializedName

class Area {

    @SerializedName("_id") var _id: Int? = null
    @SerializedName("E_Pic_URL") var e_pic_url: String? = null
    @SerializedName("E_Info") var e_info: String? = null
    @SerializedName("E_no") var e_no: String? = null
    @SerializedName("E_Category") var e_category: String? = null
    @SerializedName("E_Name") var e_name: String? = null
    @SerializedName("E_Memo") var e_memo: String? = null
    @SerializedName("E_URL") var e_url: String? = null

}