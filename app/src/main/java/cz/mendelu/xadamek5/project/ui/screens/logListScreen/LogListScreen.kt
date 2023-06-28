@file:RequiresApi(Build.VERSION_CODES.O)
package cz.mendelu.xadamek5.project.ui.screens.logListScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.mendelu.xadamek5.project.R
import cz.mendelu.xadamek5.project.model.Log
import cz.mendelu.xadamek5.project.navigation.INavigationRouter
import cz.mendelu.xadamek5.project.ui.elements.BackArrowScreen
import cz.mendelu.xadamek5.project.ui.theme.basicMargin
import cz.mendelu.xadamek5.project.utils.DateUtils
import org.koin.androidx.compose.getViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@Composable
fun LogListScreen(navigation: INavigationRouter, viewModel: LogListScreenViewModel = getViewModel()){

   val logs = remember { mutableStateListOf<Log>() }
   var showPlaceholder: Boolean by rememberSaveable{ mutableStateOf(false) }

   viewModel.logListUIState.value.let {
       when(it){
           is LogListScreenUIState.Default -> {
               LaunchedEffect(it){
                   viewModel.loadLogs()
               }
           }
           is LogListScreenUIState.Success -> {
               if (it.logs.isEmpty()) showPlaceholder = true
               logs.clear()
               logs.addAll(it.logs)
           }
       }
   }

    BackArrowScreen(topBarTitle = stringResource(R.string.my_logs), onBackClick = { navigation.returnBack() }) {
        LogListScreenContent(navigation = navigation, logs = logs, paddingValues = it, showPlaceholder = showPlaceholder)
    }

}

@Composable
fun LogListScreenContent(navigation: INavigationRouter,
                         logs: List<Log>,
                         showPlaceholder: Boolean,
                         paddingValues: PaddingValues){

    if(showPlaceholder){
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.empty),
                contentDescription = "Empty logs",
                modifier = Modifier.width(350.dp)
            )
        }
    } else {

        Column() {

            logs.forEach {
                LogRow(log = it, onEditClick = {
                    navigation.navigateToNewLogScreen(it.id)
                }, onRowClick = {
                    navigation.navigateToLogDetailScreen(it.id)
                })


            }
        }
    }

}


@Composable
fun LogRow(log: Log,
            onEditClick: () -> Unit,
            onRowClick: () -> Unit
           ){

    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .clickable(onClick = onRowClick)){
        Column(modifier = Modifier.weight(1f)) {
            Text( DateUtils.getDateString(log.date), fontWeight = FontWeight.Bold)
            Text(log.description, maxLines = 3)
        }
        
            Icon(painter = painterResource(R.drawable.edit), contentDescription = stringResource(R.string.edit_log),
            modifier = Modifier.clickable(onClick = onEditClick))

    }
    Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(horizontal = 15.dp, vertical = 20.dp))


}