package ca.qc.cgodin.restaurant.RoomDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {





    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoris(favoris: Favoris)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUserFavorisCrossRef(crossRef: UserFavorisCrossRef)


    @Transaction
    @Query("SELECT * from user_table1 ORDER BY Username ASC")
    fun getUsers(): LiveData<List<User>>

    @Transaction
    @Query("SELECT * FROM user_table1 WHERE id=(:id)")
    fun getUser(id: Int): LiveData<User?>

    @Transaction
    @Query("SELECT * FROM user_table1 WHERE Username=(:name)")
    fun getUserByUsername(name: String): LiveData<List<User>>

    @Transaction
    @Query("SELECT * FROM userfavoriscrossref WHERE UserId=(:id)")
    fun getFavoris(id: Int): LiveData<List<UserFavorisCrossRef>>

  /*  @Transaction
    @Query("SELECT id FROM user_table1 WHERE Email=(:email)")
    fun getUserIdByEmail(email: String): LiveData<List<User>>*/

    @Transaction
    @Query("SELECT * FROM user_table1 WHERE Email=(:email)")
    fun getUserByEmail(email: String): LiveData<List<User>>

    @Transaction
    @Query("SELECT * from favoris_table1 ORDER BY id ASC")
    fun getFavoris(): LiveData<List<Favoris>>


    @Transaction
    @Query("SELECT * from favoris_table1 WHERE id=(:id)")
    fun getFavoris2(id: Int): LiveData<List<Favoris>>





    @Transaction
    @Query("SELECT * from UserFavorisCrossRef ORDER BY UserId ASC")
    fun getUserFavorisCrossRef(): LiveData<List<UserFavorisCrossRef>>







}