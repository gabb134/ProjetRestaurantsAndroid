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

    val allFavoris: LiveData<List<Favoris>>

    init {
        val userDao : UserDao = UserRoomDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        allUsers = repository.allUsers
        allFavoris = repository.allFavoris
    }

    fun insert(user: User) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(user)
    }
    fun insertFavoris(favoris: Favoris) = viewModelScope.launch(Dispatchers.IO){
        repository.insertFavoris(favoris)
    }
    fun getFavoris() = repository.getFavoris()

    fun insertUserFavorisCrossRef(userFavorisCrossRef: UserFavorisCrossRef) = viewModelScope.launch(Dispatchers.IO){
        repository.insertUserFavorisCrossRef(userFavorisCrossRef)
    }

    fun searchUser(email:String) :LiveData<List<User>> {
        return repository.searchUser(email)
    }


}