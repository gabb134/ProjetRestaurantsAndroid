package ca.qc.cgodin.restaurant.RoomDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface UserDao {

    @Query("SELECT * from user_table ORDER BY Username ASC")
    fun getUsers(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE id=(:id)")
    fun getUser(id: Int): LiveData<User?>

    @Query("SELECT * FROM user_table WHERE Username=(:name)")
    fun getUserByUsername(name: String): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE Email=(:email)")
    fun getUserByEmail(email: String): LiveData<List<User>>



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)

}