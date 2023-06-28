package cz.mendelu.xadamek5.project.ui.screens.updateProfileScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.xadamek5.project.architecture.BaseViewModel
import cz.mendelu.xadamek5.project.database.image.IAppRepository
import cz.mendelu.xadamek5.project.ui.screens.LogDetailScreen.LogDetailScreenUIState
import cz.mendelu.xadamek5.project.ui.screens.settingsScreen.SettingsScreenUIState
import kotlinx.coroutines.launch

class UpdateProfileScreenViewModel(private val repository: IAppRepository) : BaseViewModel(), UpdateProfileScreenActions {

    val updateProfileScreenUIState: MutableState<UpdateProfileScreenUIState> = mutableStateOf(
        UpdateProfileScreenUIState.Default())

    var data: UpdateProfileScreenData = UpdateProfileScreenData()



    fun loadProfile(){
        launch {
            launch {
                data.profile = repository.getProfile()
                updateProfileScreenUIState.value = UpdateProfileScreenUIState.Success()
            }
        }
    }

    override fun updateProfile(imageUri: String) {
        data.profile.imageUri = imageUri

        if(data.profile.imageUri != ""
            && data.profile.nickname != ""
            && data.profile.addiction != "") {

            launch {
                repository.updateProfile(data.profile)
                updateProfileScreenUIState.value = UpdateProfileScreenUIState.ProfileUpdated()
            }
        } else {
            if(data.profile.imageUri == ""){
                data.emptyProfilePic = 1
                updateProfileScreenUIState.value = UpdateProfileScreenUIState.ProfileStateChanged()
            } else {
                data.emptyProfilePic = null
            }
            if(data.profile.addiction == ""){
                data.emptyAddition = 1
                updateProfileScreenUIState.value = UpdateProfileScreenUIState.ProfileStateChanged()
            } else {
                data.emptyAddition = null
            }
            if(data.profile.nickname == ""){
                data.emptyNick = 1
                updateProfileScreenUIState.value = UpdateProfileScreenUIState.ProfileStateChanged()
            } else {
                data.emptyNick = null
            }
        }



    }

    override fun onNickChange(nickname: String) {
        data.profile.nickname = nickname
        updateProfileScreenUIState.value = UpdateProfileScreenUIState.ProfileStateChanged()
    }

    override fun onAddictionChange(addiction: String) {
        data.profile.addiction = addiction
        updateProfileScreenUIState.value = UpdateProfileScreenUIState.ProfileStateChanged()
    }

    override fun onProfilePicChange(profilePic: String) {
        data.profile.imageUri = profilePic
        updateProfileScreenUIState.value = UpdateProfileScreenUIState.ProfileStateChanged()
    }


}