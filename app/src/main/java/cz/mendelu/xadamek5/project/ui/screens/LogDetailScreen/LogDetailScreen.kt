@file:RequiresApi(Build.VERSION_CODES.O)

package cz.mendelu.xadamek5.project.ui.screens.LogDetailScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.maps.android.compose.Circle
import cz.mendelu.xadamek5.project.R
import cz.mendelu.xadamek5.project.database.image.IAppRepository
import cz.mendelu.xadamek5.project.model.Log
import cz.mendelu.xadamek5.project.navigation.INavigationRouter
import cz.mendelu.xadamek5.project.ui.elements.BackArrowScreen
import cz.mendelu.xadamek5.project.ui.elements.FilledCircleText
import cz.mendelu.xadamek5.project.ui.theme.basicMargin
import org.koin.androidx.compose.getViewModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@Composable
fun LogDetailScreen(navigation: INavigationRouter, viewModel: LogDetailScreenViewModel = getViewModel(), id: Long?){

    viewModel.logId = id

    val data: LogDetailScreenData by remember {
        mutableStateOf(viewModel.data)
    }

    viewModel.logDetailScreenUIState.value.let {
        when(it){
            is LogDetailScreenUIState.Default -> {
                LaunchedEffect(it){
                    viewModel.initLog()
                }
            }
            is LogDetailScreenUIState.Success -> {

            }
            LogDetailScreenUIState.LogDeleted -> {
                LaunchedEffect(it){
                    navigation.returnBack()
                }

            }
        }
    }

    BackArrowScreen(topBarTitle = stringResource(R.string.log_detail), onBackClick = { navigation.returnBack() },
    actions = {
        IconButton(onClick = { viewModel.deleteLog() }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = stringResource(R.string.delete_log))
        }
    }
    ) {
        LogDetailScreenContent(data = data)
    }


}


@Composable
fun LogDetailScreenContent(data: LogDetailScreenData){

    Row(modifier = Modifier.fillMaxWidth() ,horizontalArrangement = Arrangement.SpaceBetween) {
        val instant = Instant.ofEpochMilli(data.log.date)
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val formattedDate = localDateTime.format(formatter)
        Column(modifier = Modifier.weight(1f)) {
            Text(
                formattedDate.toString(),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 15.dp)
            )


            Spacer(modifier = Modifier.height(basicMargin()))
            Text(text = data.log.description, modifier = Modifier.padding(horizontal = 15.dp))
        }
        Box(modifier = Modifier.offset(x = -5.dp)) {
            FilledCircleText(
                text = data.log.feeling.toString(),
                backgroundColor = Color.Blue,
                textColor = Color.White,
                size = 50,
                fontSize = 30
            )
        }

    }


}