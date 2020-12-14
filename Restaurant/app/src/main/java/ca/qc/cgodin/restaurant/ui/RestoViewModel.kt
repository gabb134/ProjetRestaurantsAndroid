package ca.qc.cgodin.restaurant.ui


import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cgodin.restaurant.modeleDetailsZomato.DetailResto
import ca.qc.cgodin.restaurant.modeleMenu.Menu
import ca.qc.cgodin.restaurant.modeleSearch.NearbySearch
import ca.qc.cgodin.restaurant.repository.RestaurantRepository
import kotlinx.coroutines.launch
import ca.qc.cgodin.restaurant.modeleSearchZomato.SearchInfo


class RestoViewModel(val restaurantRepository: RestaurantRepository)  : ViewModel() {

    val resto: MutableLiveData<NearbySearch> = MutableLiveData()

    val details: MutableLiveData<DetailResto> = MutableLiveData()

    val menu: MutableLiveData<Menu> = MutableLiveData()

    val searchVal: MutableLiveData<SearchInfo> = MutableLiveData()


    init{
       // getRestoNearby("1500")
       getNearbyResto("1500")
    }
 /*   fun getRestoNearby(rayon: String) = viewModelScope.launch {
        val response = restaurantRepository.getNearbyRestaurants(rayon)

        resto.postValue(response.body())
    }*/

    fun getDetailsResto(res_id: Int) = viewModelScope.launch {
        val response = restaurantRepository.getDetailsRestaurants(res_id)
        details.postValue(response.body())

    }

    fun getNearbyResto(rayon: String) = viewModelScope.launch {
        val response = restaurantRepository.getNearbyResto(rayon)
        searchVal.postValue(response.body())
    }

    fun getMenu(res_id: Int) = viewModelScope.launch {
        val response = restaurantRepository.getMenu(res_id)
        menu.postValue(response.body())
    }

    fun getSearchByName(q: String, rayon: String,start: String) = viewModelScope.launch {

        val response = restaurantRepository.getSearchByName(q, rayon, start)
        searchVal.postValue(response.body())

    }



}

