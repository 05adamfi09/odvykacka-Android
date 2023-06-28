package cz.mendelu.xadamek5.project.ui.screens.newLog

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.mendelu.xadamek5.project.navigation.INavigationRouter
import cz.mendelu.xadamek5.project.ui.elements.BackArrowScreen
import cz.mendelu.xadamek5.project.ui.elements.InfoElement
import org.koin.androidx.compose.getViewModel
import cz.mendelu.xadamek5.project.R
import cz.mendelu.xadamek5.project.ui.elements.MyButton
import cz.mendelu.xadamek5.project.ui.elements.TextInputField
import cz.mendelu.xadamek5.project.ui.theme.basicMargin
import cz.mendelu.xadamek5.project.utils.DateUtils
import java.util.*


@Composable
fun NewLogScreen(navigation: INavigationRouter, viewModel: NewLogScreenViewModel = getViewModel(), id: Long?){

    viewModel.logId = id

    val showDialogState: Boolean by viewModel.showDialog.collectAsState()


    var data: NewLogData by remember {
        mutableStateOf(NewLogData())
    }

    viewModel.newLogScreensUIState.value.let {
        when (it) {
            NewLogScreensUIState.Default -> {
                // v případě, že potřebujeme jen čekat co se bude dít.
            }
            NewLogScreensUIState.LogLoaded -> {
                data = viewModel.data
                data.loadingScreen = false
            }
            NewLogScreensUIState.LogSaved -> {
                LaunchedEffect(it) {
                    navigation.returnBack()
                }
            }
            NewLogScreensUIState.Loading -> {
                LaunchedEffect(it) {
                    viewModel.initLog()
                }
            }
            is NewLogScreensUIState.LogStateChanged -> {
                data = viewModel.data
            }
        }
    }

    BackArrowScreen(topBarTitle =
    if (id == null){
        stringResource(R.string.log_day)} else stringResource(R.string.update_log),
        onBackClick = { navigation.returnBack() }) {
        NewLogScreenContent(data = data, navigation = navigation, actions = viewModel, showDialogState = showDialogState)
    }


}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewLogScreenContent(
    data: NewLogData,
    navigation: INavigationRouter,
    actions: NewLogScreenActions,
    showDialogState: Boolean
){

    if (!data.loadingScreen) {
        val calendar = Calendar.getInstance()

        if (data.log.date == 0L) {
            data.log.date = calendar.timeInMillis
        }

        val y = calendar.get(Calendar.YEAR)
        val m = calendar.get(Calendar.MONTH)
        val d = calendar.get(Calendar.DAY_OF_MONTH)


        val datePickerDialog = DatePickerDialog(
            LocalContext.current,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                actions.onDateChange(DateUtils.getUnixTime(year, month, dayOfMonth))
            }, y, m, d
        )


        //pole data
        InfoElement(
            value = DateUtils.getDateString(data.log.date),
            hint = stringResource(R.string.date),
            leadingIcon = R.drawable.date,
            onClick = {
                datePickerDialog.show()
            }, onClearClick = {
                actions.onDateChange(calendar.timeInMillis)
            },
            errorMessage = if (data.logDateFuture != null) stringResource(R.string.you_cant_log_future) else ""

        )

        //dialog když je den logován podruhé
        if(showDialogState) {
            AlertDialog(onDismissRequest = { actions.onDialogConfirm() },
                confirmButton = {
                    TextButton(onClick = { actions.onDialogConfirm() }) {
                        Text("OK")
                    }
                },
                title = { Text(stringResource(id = R.string.this_day_is_already_loged)) })
        }

        Spacer(modifier = Modifier.height(basicMargin()))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Icon(painter = painterResource(R.drawable.bolt), contentDescription = null)

            Text( stringResource(R.string.feeling) + ": " + data.log.feeling.toString())
        }

        Slider(
            value = data.log.feeling.toFloat(),
            onValueChange = { actions.onFeelingChange(it.toInt()) },
            valueRange = 1f..10f,
            steps = 9,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(basicMargin()))


        //pole pro popis
        TextInputField(
            value = data.log.description,
            hint = stringResource(R.string.description),
            onValueChange = {
                actions.onTextChange(it)
            },
            errorMessage = if (data.logEmptyError != null) stringResource(R.string.need_to_fill_description) else "",
            maxLines = 8
        )

        Spacer(modifier = Modifier.height(basicMargin()))


        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            MyButton(onClick = { actions.saveLog() }, text = stringResource(R.string.save))
        }


    }
}