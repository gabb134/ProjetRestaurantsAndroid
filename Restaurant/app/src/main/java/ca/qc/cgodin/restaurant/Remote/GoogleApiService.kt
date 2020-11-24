package ca.qc.cgodin.restaurant.Remote

import ca.qc.cgodin.restaurant.modeleSearch.NearbySearch
import ca.qc.cgodin.restaurant.modeleDetail.Details

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
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

    @GET("details/json?")
    suspend fun getDetailsResto(
        @Query("place_id")
        place_id: String = "",
        @Query("key")
        apiKey: String = "AIzaSyDm2ot4-CDQg6M6ZJSbz0K21cXUklAQYQ0"
    ) : Response<Details>


  /*  @GET("api/v2.1/search")
    fun searchApi(@Query("lat") queryParameters1: String,
                  @Query("lon")queryParams2: String,
                  @Query("sort")queryParams3: String):Call<SearchResponse>*/


}