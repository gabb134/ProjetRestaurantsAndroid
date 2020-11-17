package ca.qc.cgodin.restaurant.Remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitClient {
    companion object{
        private const val BASE_URL = "https://maps.googleapis.com/maps/api/place/"

        private val retroFitClient by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val retrofitService by lazy {
            retroFitClient.create(GoogleApiService::class.java)
        }
    }
}