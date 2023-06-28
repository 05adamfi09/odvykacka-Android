package cz.mendelu.xadamek5.project.functions

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import java.io.File


@Composable
fun ImagePicker(onImageLoaded: (Uri) -> Unit){
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let { onImageLoaded(it) }
    }

    Button(onClick = { launcher.launch("image/*") }) {
        Text(text = "Select Image")
    }

}


fun saveImageToLocal(context:Context, uri: Uri) {
    val inputStream = context.contentResolver.openInputStream(uri)
    val file = File(context.getExternalFilesDir(null), "profile_picture.jpg")

    inputStream?.use { input ->
        file.outputStream().use { output ->
            input.copyTo(output)
        }
    }
}
