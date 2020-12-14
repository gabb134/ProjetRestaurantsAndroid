package ca.qc.cgodin.restaurant.RoomDatabase
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "favoris_table1")
data class Favoris(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="id")
    val RestoId: Long
    )
