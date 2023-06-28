package cz.mendelu.xadamek5.project.ui.screens.newLog

sealed class NewLogScreensUIState {
    object Default: NewLogScreensUIState()
    object LogSaved: NewLogScreensUIState()
    object Loading : NewLogScreensUIState()
    object LogLoaded : NewLogScreensUIState()
    class LogStateChanged() : NewLogScreensUIState()


}