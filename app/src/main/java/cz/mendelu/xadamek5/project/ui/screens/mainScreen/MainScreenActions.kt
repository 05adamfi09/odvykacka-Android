package cz.mendelu.xadamek5.project.ui.screens.mainScreen

import android.net.Uri
import cz.mendelu.xadamek5.project.model.Log
import cz.mendelu.xadamek5.project.model.Profile

interface MainScreenActions{

    fun countDays(logList: List<Log>)

    fun countDaysFeeling(logList: List<Log>)

}