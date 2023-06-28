package cz.mendelu.xadamek5.project.ui.screens.updateProfileScreen

import cz.mendelu.xadamek5.project.model.Profile
import cz.mendelu.xadamek5.project.ui.screens.settingsScreen.SettingsScreenUIState

sealed class UpdateProfileScreenUIState {

    class Default : UpdateProfileScreenUIState()
    class Success() : UpdateProfileScreenUIState()
    class ProfileUpdated() : UpdateProfileScreenUIState()
    class ProfileStateChanged() : UpdateProfileScreenUIState()


}