package ca.qc.cgodin.restaurant.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.qc.cgodin.restaurant.repository.RestaurantRepository

class RestoViewModelProviderFactory (val restoRepository: RestaurantRepository): ViewModelProvider.Factory{
     override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RestoViewModel(restoRepository) as T   }
 }