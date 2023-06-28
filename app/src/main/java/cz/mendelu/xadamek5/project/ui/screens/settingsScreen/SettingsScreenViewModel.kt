package cz.mendelu.xadamek5.project.ui.screens.settingsScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import cz.mendelu.xadamek5.project.architecture.BaseViewModel
import cz.mendelu.xadamek5.project.database.image.IAppRepository
import cz.mendelu.xadamek5.project.ui.activities.CreateProfileActivity
import kotlinx.coroutines.launch

class SettingsScreenViewModel(private val repository: IAppRepository) : BaseViewModel(), SettingsScreenActions {

    val settingsScreenUIState: MutableState<SettingsScreenUIState> = mutableStateOf(SettingsScreenUIState.Default())

    fun loadProfile(){
        launch {
            settingsScreenUIState.value = SettingsScreenUIState.Success(repository.getProfile())
        }
    }



}