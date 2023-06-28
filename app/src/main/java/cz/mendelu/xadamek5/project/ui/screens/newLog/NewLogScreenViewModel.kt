@file:RequiresApi(Build.VERSION_CODES.O)
package cz.mendelu.xadamek5.project.ui.screens.newLog

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import cz.mendelu.xadamek5.project.R
import cz.mendelu.xadamek5.project.architecture.BaseViewModel
import cz.mendelu.xadamek5.project.constants.Constants
import cz.mendelu.xadamek5.project.database.image.IAppRepository
import cz.mendelu.xadamek5.project.ui.screens.mainScreen.MainScreenUIState
import cz.mendelu.xadamek5.project.utils.DateUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewLogScreenViewModel(private val repository: IAppRepository, private val savedStateHandle: SavedStateHandle) : BaseViewModel(),
    NewLogScreenActions {

    val newLogScreensUIState: MutableState<NewLogScreensUIState> = mutableStateOf(NewLogScreensUIState.Loading)

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    override fun onOpenDialogClicked() {
        _showDialog.value = true
    }

    override fun onDialogConfirm() {
        _showDialog.value = false
    }

    var logId: Long? = null

    var data: NewLogData = NewLogData()
    get() {
        if (!savedStateHandle.contains(Constants.LOG_DATA)){
            savedStateHandle.set(Constants.LOG_DATA, NewLogData())
        }
        return savedStateHandle.get(Constants.LOG_DATA)!!
    }
    set(value) {
        field = value
        savedStateHandle.set(Constants.LOG_DATA, field)
    }


    override fun saveLog() {


        //TODO podmínka aby se nedalo uložit na stejný datum
        if (data.log.description.isEmpty()){
            data.logEmptyError = 1
            newLogScreensUIState.value = NewLogScreensUIState.LogStateChanged()
        } else {
            data.logEmptyError = null
        }
        if (DateUtils.getCurrentMillis() < data.log.date){
            data.logDateFuture = 1
            newLogScreensUIState.value = NewLogScreensUIState.LogStateChanged()
        } else {
            data.logDateFuture = null
        }

        var todayIsLoged: Boolean = false

        if(data.logs != null) {
            for (log in data.logs!!) {
                if (DateUtils.getDayFromEpoch(data.log.date) == DateUtils.getDayFromEpoch(log.date)) {
                    println("Today is already logged")
                    todayIsLoged = true
                    data.todayIsLoged = 1
                    onOpenDialogClicked()
                    break
                } else {
                    data.todayIsLoged = null
                }
            }
        }

        if (!data.log.description.isEmpty()
            && DateUtils.getCurrentMillis() >= data.log.date
            && !todayIsLoged) {
            launch {
                if (logId == null) {
                    repository.insert(data.log)
                } else {
                    repository.update(data.log)
                }
                newLogScreensUIState.value = NewLogScreensUIState.LogSaved
            }
        }
    }

    override fun onTextChange(description: String) {
        data.log.description = description
        newLogScreensUIState.value = NewLogScreensUIState.LogStateChanged()
    }

    override fun onDateChange(date: Long) {
        data.log.date = date
        newLogScreensUIState.value = NewLogScreensUIState.LogStateChanged()
    }

    override fun onFeelingChange(feeling: Int) {
        data.log.feeling = feeling
        newLogScreensUIState.value = NewLogScreensUIState.LogStateChanged()
    }


    override fun initLog() {
        if (logId != null) {
            launch {
                data.log = repository.findById(logId!!)
                newLogScreensUIState.value = NewLogScreensUIState.LogLoaded
            }
        } else {
            newLogScreensUIState.value = NewLogScreensUIState.LogLoaded
        }
        launch {
            repository.getAll().collect{
                data.logs = it
            }
        }
    }


}