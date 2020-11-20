package ca.qc.cgodin.restaurant.ui


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cgodin.restaurant.modeleSearch.NearbySearch
import ca.qc.cgodin.restaurant.repository.RestaurantRepository
import kotlinx.coroutines.launch
import ca.qc.cgodin.restaurant.modeleDetail.Details


class RestoViewModel(val restaurantRepository: RestaurantRepository)  : ViewModel() {

    val resto: MutableLiveData<NearbySearch> = MutableLiveData()

    val details: MutableLiveData<Details> = MutableLiveData()

    init{
        getRestoNearby("1500")
    }
    fun getRestoNearby(rayon: String) = viewModelScope.launch {
        val response = restaurantRepository.getNearbyRestaurants(rayon)

        resto.postValue(response.body())
    }

    fun getDetailsResto(place_id: String) = viewModelScope.launch {
        val response = restaurantRepository.getDetailsRestaurants(place_id)
        details.postValue(response.body())
    }




}

