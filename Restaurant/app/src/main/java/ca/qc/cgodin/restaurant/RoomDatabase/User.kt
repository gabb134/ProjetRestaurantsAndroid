package ca.qc.cgodin.restaurant.RoomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val Id:Int,
    val Username:String,
    val Password:String,
    val Email:String

)