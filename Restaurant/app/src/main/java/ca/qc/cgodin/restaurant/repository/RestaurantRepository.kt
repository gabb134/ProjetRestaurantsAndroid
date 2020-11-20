package ca.qc.cgodin.restaurant.repository

import ca.qc.cgodin.restaurant.Remote.RetroFitClient
import java.lang.ref.PhantomReference

class RestaurantRepository {

    suspend fun getNearbyRestaurants(rayon: String) =
        RetroFitClient.retrofitService.getNearbyPlace(radius = rayon)

    suspend fun getDetailsRestaurants(place_id: String) =
        RetroFitClient.retrofitService.getDetailsResto(place_id = place_id)
}