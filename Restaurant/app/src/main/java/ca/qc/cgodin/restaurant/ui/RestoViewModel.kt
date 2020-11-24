package ca.qc.cgodin.restaurant.ui


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cgodin.restaurant.modeleDetailsZomato.DetailResto
import ca.qc.cgodin.restaurant.modeleSearch.NearbySearch
import ca.qc.cgodin.restaurant.repository.RestaurantRepository
import kotlinx.coroutines.launch
import ca.qc.cgodin.restaurant.modeleSearchZomato.SearchInfo


class RestoViewModel(val restaurantRepository: RestaurantRepository)  : ViewModel() {

    val resto: MutableLiveData<NearbySearch> = MutableLiveData()

    val details: MutableLiveData<DetailResto> = MutableLiveData()

    val searchVal: MutableLiveData<SearchInfo> = MutableLiveData()

    init{
        getRestoNearby("1500")
        getMenu("1500")
    }
    fun getRestoNearby(rayon: String) = viewModelScope.launch {
        val response = restaurantRepository.getNearbyRestaurants(rayon)

        resto.postValue(response.body())
    }

    fun getDetailsResto(res_id: Int) = viewModelScope.launch {
        val response = restaurantRepository.getDetailsRestaurants(res_id)
        details.postValue(response.body())

    }

    fun getMenu(rayon: String) = viewModelScope.launch {
        val response = restaurantRepository.getMenu(rayon)
        searchVal.postValue(response.body())
    }
}

