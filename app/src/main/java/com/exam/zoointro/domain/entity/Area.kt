package com.exam.zoointro.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Area(@SerializedName("_id") var _id: Int?,
           @SerializedName("E_Pic_URL") var e_pic_url: String?,
           @SerializedName("E_Info") var e_info: String?,
           @SerializedName("E_no") var e_no: String?,
           @SerializedName("E_Category") var e_category: String?,
           @SerializedName("E_Name") var e_name: String?,
           @SerializedName("E_Memo") var e_memo: String?,
           @SerializedName("E_URL") var e_url: String?): Parcelable