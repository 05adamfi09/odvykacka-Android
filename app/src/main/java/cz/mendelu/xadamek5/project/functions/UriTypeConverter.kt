package cz.mendelu.xadamek5.project.functions

import android.net.Uri
import androidx.room.TypeConverter

class UriTypeConverter {
    @TypeConverter
    fun fromUri(uri: Uri): String {
        return uri.toString()
    }

    @TypeConverter
    fun toUri(uriString: String): Uri {
        return Uri.parse(uriString)
    }
}