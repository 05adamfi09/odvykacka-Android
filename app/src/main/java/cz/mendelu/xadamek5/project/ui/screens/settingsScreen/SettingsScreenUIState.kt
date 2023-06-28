package cz.mendelu.xadamek5.project.ui.screens.settingsScreen

import cz.mendelu.xadamek5.project.model.Profile

sealed class SettingsScreenUIState {
    class Default : SettingsScreenUIState()
    class Success(val profile: Profile) : SettingsScreenUIState()
}