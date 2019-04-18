package com.exam.zoointro.data.net

import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class Rest {

    companion object {
        private const val BASE_URL = "https://data.taipei"
        const val SCOPE = "resourceAquire"
        const val MEMBER_NAME_RESULT = "result"
        const val MEMBER_NAME_RESULTS = "results"
        const val AREA_RID = "5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a"
        const val PLANT_RID = "f18de02f-b6c9-47c0-8cda-50efad621c14"

        val mApi
            get() = ClientManager.getClient()?.create(Api::class.java)
    }

    object ClientManager {
        private var retrofit: Retrofit? = null
        init {
            val okHttpClient = OkHttpClient()
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getClient() = retrofit
    }

    interface Api {
        @GET("/opendata/datalist/apiAccess")
        fun getInfo(@Query("scope") scope: String?,
                    @Query("rid") rid: String?): Call<JsonObject>
    }

}