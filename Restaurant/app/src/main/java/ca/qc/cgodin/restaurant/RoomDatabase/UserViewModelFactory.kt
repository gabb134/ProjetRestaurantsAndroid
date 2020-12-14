package ca.qc.cgodin.restaurant.RoomDatabase

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class UserViewModelFactory(private val application: Application): ViewModelProvider.AndroidViewModelFactory(application)  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(UserViewModel::class.java)){
            UserViewModel(application) as T
        }else{
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}