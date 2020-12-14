package ca.qc.cgodin.restaurant.RoomDatabase

import androidx.room.Entity

@Entity(primaryKeys = ["UserId","RestoId"])
data class UserFavorisCrossRef(
    val UserId:Int,
    val RestoId: Long
)