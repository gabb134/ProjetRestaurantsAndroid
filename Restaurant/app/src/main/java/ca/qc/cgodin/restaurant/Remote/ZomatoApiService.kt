package ca.qc.cgodin.restaurant.Remote

import ca.qc.cgodin.restaurant.modeleDetailsZomato.DetailResto
import ca.qc.cgodin.restaurant.modeleMenu.Menu
import ca.qc.cgodin.restaurant.modeleSearchZomato.SearchInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ZomatoApiService {

    @Headers("user-key: ae896dac08969c9b6a29ebb48383afe8"
    )
    @GET("search")
    suspend fun getSearchMenu(
        @Query("entity_id")
        entity_id: String = "294",
        @Query("entity_type")
        entity_type: String = "city",
        @Query("lat")
        lat: String = "45.4974481457364",
        @Query("lon")
        lon: String = "-73.79050905954415",
        @Query("radius")
        radius: String = "1",
        @Query("sort")
        sort: String = "real_distance",
        @Query("order")
        order: String = "desc"
    ) : Response<SearchInfo>

    @Headers("user-key: ae896dac08969c9b6a29ebb48383afe8"
    )
    @GET("restaurant")
    suspend fun getDetails(
        @Query("res_id")
        res_id: Int ,
    ) : Response<DetailResto>

    @Headers("user_key: ae896dac08969c9b6a29ebb48383afe8"
    )
    @GET("dailymenu")
    suspend fun getMenu(
        @Query("res_id")
        res_id: Int ,
    ) : Response<Menu>

    @Headers("user-key: ae896dac08969c9b6a29ebb48383afe8"
    )
    @GET("search?")
    suspend fun getSearchRestoNameOrType(
        @Query("entity_id")
        entity_id: String = "294",
        @Query("entity_type")
        entity_type: String = "city",
        @Query("lat")
        lat: String = "45.4974481457364",
        @Query("lon")
        lon: String = "-73.79050905954415",
        @Query("radius")
        radius: String = "1",
        @Query("sort")
        sort: String = "real_distance",
        @Query("order")
        order: String = "desc",
        @Query("q")
        q : String = "",
        @Query("start")
        start : String = "0"
    ) : Response<SearchInfo>
}