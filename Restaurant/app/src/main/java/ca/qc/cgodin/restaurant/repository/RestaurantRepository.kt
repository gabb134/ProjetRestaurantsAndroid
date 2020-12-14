package ca.qc.cgodin.restaurant.repository

import ca.qc.cgodin.restaurant.Remote.RetroFitClient
import ca.qc.cgodin.restaurant.Remote.RetroFitZomato
import java.lang.ref.PhantomReference

class RestaurantRepository {

    //suspend fun getNearbyRestaurants(rayon: String) =
  //     RetroFitClient.retrofitService.getNearbyPlace(radius = rayon)

    suspend fun getDetailsRestaurants(res_id: Int) =
        RetroFitZomato.retrofitServiceZomato.getDetails(res_id = res_id)

    suspend fun getNearbyResto(rayon: String) =
        RetroFitZomato.retrofitServiceZomato.getSearchMenu(radius = rayon)

    suspend fun getMenu(res_id: Int) =
        RetroFitZomato.retrofitServiceZomato.getMenu(res_id = res_id)
    suspend fun getSearchByName(q: String , rayon: String, start: String) =
        RetroFitZomato.retrofitServiceZomato.getSearchRestoNameOrType(q = q, radius = rayon, start = start)
}