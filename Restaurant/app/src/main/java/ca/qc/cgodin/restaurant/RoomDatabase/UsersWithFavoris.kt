package ca.qc.cgodin.restaurant.RoomDatabase

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class UsersWithFavoris (
    @Embedded val user:User,
    @Relation(
        parentColumn = "UserId",
        entityColumn = "FavorisId",
        associateBy = Junction(UserFavorisCrossRef::class)
    )
    val favoris:List<Favoris>
)