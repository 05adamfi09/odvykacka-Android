package cz.mendelu.xadamek5.project.ui.screens.LogDetailScreen

import cz.mendelu.xadamek5.project.model.Log

class LogDetailScreenData : java.io.Serializable {
    var loadingScreen: Boolean = true
    var log: Log = Log(0,"")
}