package ca.qc.cgodin.restaurant.repository

import androidx.lifecycle.LiveData
import ca.qc.cgodin.restaurant.Remote.RetroFitClient
import ca.qc.cgodin.restaurant.Remote.RetroFitZomato
import ca.qc.cgodin.restaurant.RoomDatabase.Favoris
import ca.qc.cgodin.restaurant.RoomDatabase.User
import ca.qc.cgodin.restaurant.RoomDatabase.UserDao
import ca.qc.cgodin.restaurant.RoomDatabase.UserFavorisCrossRef
import java.lang.ref.PhantomReference

class RestaurantRepository(private val userDao: UserDao) {
    val allUsers: LiveData<List<User>> = userDao.getUsers()

    val allFavoris : LiveData<List<Favoris>> =userDao.getFavoris()

    suspend fun insert(user: User){
        userDao.insert(user)
    }
    suspend fun insertFavoris(favoris: Favoris){
        userDao.insertFavoris(favoris)
    }
    suspend fun insertUserFavorisCrossRef(userFavorisCrossRef: UserFavorisCrossRef){
        userDao.insertUserFavorisCrossRef(userFavorisCrossRef)
    }

    /*suspend fun getUserIdByEmail(email: String){
        userDao.getUserIdByEmail(email)
    }*/
    fun searchUser(email:String): LiveData<List<User>> {
        return  userDao.getUserByEmail(email)
    }

    fun getFavoris() = userDao.getFavoris()


    suspend fun getNearbyRestaurants(rayon: String) =
        RetroFitClient.retrofitService.getNearbyPlace(radius = rayon)

    suspend fun getDetailsRestaurants(res_id: Int) =
        RetroFitZomato.retrofitServiceZomato.getDetails(res_id = res_id)

    suspend fun getMenu(rayon: String) =
        RetroFitZomato.retrofitServiceZomato.getSearchMenu(radius = rayon)
}