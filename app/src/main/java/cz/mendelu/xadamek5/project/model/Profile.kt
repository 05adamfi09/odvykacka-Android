package cz.mendelu.xadamek5.project.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class Profile(

    @ColumnInfo(name = "imageUri")
    var imageUri: String,

    @ColumnInfo(name = "nickname")
    var nickname: String,

    @ColumnInfo(name = "addiction")
    var addiction: String
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
}
