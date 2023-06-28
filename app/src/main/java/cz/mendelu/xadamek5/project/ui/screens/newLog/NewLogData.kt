package cz.mendelu.xadamek5.project.ui.screens.newLog

import android.os.Build
import androidx.annotation.RequiresApi
import cz.mendelu.xadamek5.project.model.Log
import cz.mendelu.xadamek5.project.utils.DateUtils
import java.time.LocalDate
import java.util.Date

class NewLogData : java.io.Serializable {
    var loadingScreen: Boolean = true
    var log: Log = Log(0L, "", feeling = 5)
    var logEmptyError: Int? = null
    var logDateFuture: Int? = null
    var todayIsLoged: Int? = null
    var logs: List<Log>? = null
}