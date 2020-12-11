package ca.qc.cgodin.restaurant.RoomDatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : UserRepository

    val allUsers: LiveData<List<User>>

    init {
        val userDao : UserDao = UserRoomDatabase.getDatabase(application,viewModelScope).userDao()
        repository = UserRepository(userDao)
        allUsers = repository.allUsers
    }

    fun insert(user:User) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(user)
    }
    fun searchUser(email:String) :LiveData<List<User>> {
        return repository.searchUser(email)
    }

}