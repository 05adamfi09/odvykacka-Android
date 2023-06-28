package cz.mendelu.xadamek5.project.ui.screens.logListScreen

import cz.mendelu.xadamek5.project.model.Log


sealed class LogListScreenUIState{
    class Default : LogListScreenUIState()
    class Success(val logs: List<Log>) : LogListScreenUIState()
}
