package ca.qc.cgodin.restaurant.ui

import android.media.Image
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cgodin.restaurant.modeleSearch.NearbySearch
import ca.qc.cgodin.restaurant.modeleSearch.Photo
import ca.qc.cgodin.restaurant.repository.RestaurantRepository
import kotlinx.coroutines.launch

class RestoViewModel(val restaurantRepository: RestaurantRepository)  : ViewModel() {

    val resto: MutableLiveData<NearbySearch> = MutableLiveData()

    val photos: MutableLiveData<Photo> = MutableLiveData()

    init{
        getRestoNearby("1500")
    }
    fun getRestoNearby(rayon: String) = viewModelScope.launch {
        val response = restaurantRepository.getNearbyRestaurants(rayon)

        resto.postValue(response.body())
    }



}

