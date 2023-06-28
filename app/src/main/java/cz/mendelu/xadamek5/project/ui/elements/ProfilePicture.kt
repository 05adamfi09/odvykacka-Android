package cz.mendelu.xadamek5.project.ui.elements

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import cz.mendelu.xadamek5.project.R

@Composable
fun ProfileImage(imageUri: MutableState<String>){
    val painter = rememberImagePainter(
        if(imageUri.value.isEmpty()) R.drawable.profile_pic
        else imageUri.value
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()){
            uri: Uri? ->
        uri?.let { imageUri.value = it.toString() }
    }

    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Card( shape = CircleShape, modifier = Modifier
            .padding(8.dp)
            .size(100.dp)) {
            Image(painter = painter, contentDescription = null, modifier = Modifier
                .wrapContentSize()
                .clickable {
                    launcher.launch("image/*")
                }, contentScale = ContentScale.Crop)
        }
    }



}