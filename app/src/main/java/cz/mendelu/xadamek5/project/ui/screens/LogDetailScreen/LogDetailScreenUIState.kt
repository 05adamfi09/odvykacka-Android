package cz.mendelu.xadamek5.project.ui.screens.LogDetailScreen

import cz.mendelu.xadamek5.project.model.Log

sealed class LogDetailScreenUIState {
    class Default : LogDetailScreenUIState()
    class Success() : LogDetailScreenUIState()
    object LogDeleted : LogDetailScreenUIState()
}