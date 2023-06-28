package cz.mendelu.xadamek5.project.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "logs")
data class Log(
    @ColumnInfo(name = "date")
    var date: Long,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "feeling")
    var feeling: Int = 5
    ) : Serializable{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Long? = null





}