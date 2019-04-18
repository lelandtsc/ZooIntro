package com.exam.zoointro.domain.entity

import com.google.gson.annotations.SerializedName

class Plant {

    @SerializedName("_id") var _id: Int? = null
    @SerializedName("F_Name_Latin") var f_name_latin: String? = null
    @SerializedName("F_Location") var f_location: String? = null
    @SerializedName("F_Pic01_URL") var f_pic01_url: String? = null
    @SerializedName("F_AlsoKnown") var f_alsoKnown: String? = null
    @SerializedName("F_Name_Ch") var f_name_ch: String? = null
    @SerializedName("F_Name_En") var f_name_en: String? = null
    @SerializedName("F_Brief") var f_brief: String? = null
    @SerializedName("F_Feature") var f_feature: String? = null
    @SerializedName("F_Family") var f_family: String? = null
    @SerializedName("F_Function&Application") var f_functionApplication: String? = null
    @SerializedName("F_Genus") var f_genus: String? = null
    @SerializedName("F_Update") var f_update: String? = null
}