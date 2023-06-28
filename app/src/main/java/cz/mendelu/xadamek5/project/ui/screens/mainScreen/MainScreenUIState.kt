package cz.mendelu.xadamek5.project.ui.screens.mainScreen

import cz.mendelu.xadamek5.project.model.Log
import cz.mendelu.xadamek5.project.model.Profile

sealed class MainScreenUIState {
    class Default : MainScreenUIState()
    class Success(val logs: List<Log>, val profile: Profile) : MainScreenUIState()

}