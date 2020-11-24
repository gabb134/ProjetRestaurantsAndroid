package ca.qc.cgodin.restaurant.Remote

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetroFitZomato {

    companion object{
        private const val BASE_URL = "https://developers.zomato.com/api/v2.1/"


        private val retroFitZomato by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val retrofitServiceZomato by lazy {
            retroFitZomato.create(ZomatoApiService::class.java)
        }
    }
}