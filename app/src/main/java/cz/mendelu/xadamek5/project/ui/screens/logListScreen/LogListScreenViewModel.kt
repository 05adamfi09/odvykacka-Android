package cz.mendelu.xadamek5.project.ui.screens.logListScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.xadamek5.project.architecture.BaseViewModel
import cz.mendelu.xadamek5.project.database.image.IAppRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LogListScreenViewModel (private val repository: IAppRepository) : BaseViewModel() {

    val logListUIState: MutableState<LogListScreenUIState> = mutableStateOf(LogListScreenUIState.Default())

    fun loadLogs(){
        launch {
            repository.getAll().collect() {
                logListUIState.value = LogListScreenUIState.Success(it)
            }
        }
    }

}