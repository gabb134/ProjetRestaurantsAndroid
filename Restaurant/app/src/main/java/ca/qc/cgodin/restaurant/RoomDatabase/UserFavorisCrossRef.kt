package ca.qc.cgodin.restaurant.RoomDatabase

import androidx.room.Entity

@Entity(primaryKeys = ["UserId","FavorisId"])
data class UserFavorisCrossRef (
    val UserId:Int,
    val FavorisId:Int
)