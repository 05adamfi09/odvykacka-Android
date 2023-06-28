package cz.mendelu.xadamek5.project.ui.screens.LogDetailScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.xadamek5.project.architecture.BaseViewModel
import cz.mendelu.xadamek5.project.database.image.IAppRepository
import cz.mendelu.xadamek5.project.model.Log
import kotlinx.coroutines.launch

class LogDetailScreenViewModel (private val repository: IAppRepository) : BaseViewModel(), LogDetailScreenActions{

    val logDetailScreenUIState: MutableState<LogDetailScreenUIState> = mutableStateOf(LogDetailScreenUIState.Default())

    var logId: Long? = null
    var data: LogDetailScreenData = LogDetailScreenData()


    override fun initLog() {
        if (logId != null){
            launch {
                data.log = repository.findById(logId!!)
                data.loadingScreen = false
                logDetailScreenUIState.value = LogDetailScreenUIState.Success()
            }
        } else{
            data.loadingScreen = false
            logDetailScreenUIState.value = LogDetailScreenUIState.Success()
        }
    }

    override fun deleteLog() {
        launch {
            repository.deleteLog(data.log)
            logDetailScreenUIState.value = LogDetailScreenUIState.LogDeleted
        }
    }

}