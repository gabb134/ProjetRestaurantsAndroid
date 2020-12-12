package ca.qc.cgodin.restaurant.RoomDatabase

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {
    val allUsers: LiveData<List<User>> = userDao.getUsers()

    suspend fun insert(user: User){
        userDao.insert(user)
    }

     fun searchUser(email:String): LiveData<List<User>>{
        return  userDao.getUserByEmail(email)
    }


}