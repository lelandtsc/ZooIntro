package com.exam.zoointro.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Plant(@SerializedName("_id") var _id: Int?,
            @SerializedName("F_Name_Latin") var f_name_latin: String?,
            @SerializedName("F_Location") var f_location: String?,
            @SerializedName("F_Pic01_URL") var f_pic01_url: String?,
            @SerializedName("F_AlsoKnown") var f_alsoKnown: String?,
            @SerializedName("F_Name_Ch") var f_name_ch: String?,
            @SerializedName("F_Name_En") var f_name_en: String?,
            @SerializedName("F_Brief") var f_brief: String?,
            @SerializedName("F_Feature") var f_feature: String?,
            @SerializedName("F_Family") var f_family: String?,
            @SerializedName("F_Function&Application") var f_functionApplication: String?,
            @SerializedName("F_Genus") var f_genus: String?,
            @SerializedName("F_Update") var f_update: String?): Parcelable