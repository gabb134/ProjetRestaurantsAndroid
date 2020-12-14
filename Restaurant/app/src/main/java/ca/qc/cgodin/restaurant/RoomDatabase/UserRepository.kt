package ca.qc.cgodin.restaurant.RoomDatabase

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {
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


    fun searchUser(email:String): LiveData<List<User>>{
        return  userDao.getUserByEmail(email)
    }

    fun getFavoris() = userDao.getFavoris()




}