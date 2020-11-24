package ca.qc.cgodin.restaurant.repository

import ca.qc.cgodin.restaurant.Remote.RetroFitClient
import ca.qc.cgodin.restaurant.Remote.RetroFitZomato
import java.lang.ref.PhantomReference

class RestaurantRepository {

    suspend fun getNearbyRestaurants(rayon: String) =
        RetroFitClient.retrofitService.getNearbyPlace(radius = rayon)

    suspend fun getDetailsRestaurants(res_id: Int) =
        RetroFitZomato.retrofitServiceZomato.getDetails(res_id = res_id)

    suspend fun getMenu(rayon: String) =
        RetroFitZomato.retrofitServiceZomato.getSearchMenu(radius = rayon)
}