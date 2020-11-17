package ca.qc.cgodin.restaurant.Remote

import android.media.Image
import ca.qc.cgodin.restaurant.modeleSearch.NearbySearch
import ca.qc.cgodin.restaurant.modeleSearch.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleApiService {

    @GET("nearbysearch/json?")
    suspend fun getNearbyPlace(
        @Query("location")
        location: String = "45.497260,-73.790400",
        @Query("radius")
        radius: String = "1500",
        @Query("type")
        type: String = "restaurant",
        @Query("key")
        apiKey: String = "AIzaSyDm2ot4-CDQg6M6ZJSbz0K21cXUklAQYQ0"
    ) : Response<NearbySearch>


}