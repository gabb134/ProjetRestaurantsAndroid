package ca.qc.cgodin.restaurant.RoomDatabase


import android.content.Context
import androidx.room.Database

import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [
        User::class,
        Favoris::class,
        UserFavorisCrossRef::class
    ],
    version = 1
)
abstract class UserRoomDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {
        //Singleton to prevent multiple instances of database openint at the same time
        @Volatile
        private var INSTANCE: UserRoomDatabase? = null

        fun getDatabase(context: Context): UserRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                UserRoomDatabase::class.java,
                "user_database3"
            ).build()
            return INSTANCE as UserRoomDatabase
        }
    }
}