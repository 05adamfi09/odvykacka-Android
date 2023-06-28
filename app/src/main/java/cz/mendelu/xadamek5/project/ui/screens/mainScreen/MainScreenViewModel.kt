package cz.mendelu.xadamek5.project.ui.screens.mainScreen

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.github.mikephil.charting.charts.BarChart
import cz.mendelu.xadamek5.project.architecture.BaseViewModel
import cz.mendelu.xadamek5.project.database.image.IAppRepository
import cz.mendelu.xadamek5.project.functions.flattenToList
import cz.mendelu.xadamek5.project.model.Log
import cz.mendelu.xadamek5.project.model.Profile
import cz.mendelu.xadamek5.project.ui.screens.logListScreen.LogListScreenUIState
import cz.mendelu.xadamek5.project.utils.DateUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.log

class MainScreenViewModel(private val repository: IAppRepository) : BaseViewModel(), MainScreenActions {

    val mainScreenUIState: MutableState<MainScreenUIState> = mutableStateOf(MainScreenUIState.Default())
    var days: Int = 0
    var feeeling1: Float = 0f
    var feeeling2: Float = 0f
    var feeeling3: Float = 0f
    var feeeling4: Float = 0f
    var feeeling5: Float = 0f
    var feeeling6: Float = 0f
    var feeeling7: Float = 0f
    var feeeling8: Float = 0f
    var feeeling9: Float = 0f
    var feeeling10: Float = 0f

    fun load(){
        launch {
            repository.getAll().collect() {
                mainScreenUIState.value = MainScreenUIState.Success(it, repository.getProfile())
            }
        }
    }



    override fun countDays(logList: List<Log>) {
        days = 0
        val sortedList = logList.sortedByDescending { it.date }

        val currentDayFromEpoch = DateUtils.getCurrentDayFromEpoch()
        var minuser = 0
        for (log in sortedList){
            val logDay = DateUtils.getDayFromEpoch(log.date)
            if (logDay == currentDayFromEpoch-minuser){
                days ++
                minuser ++
            }
        }

    }

    override fun countDaysFeeling(logList: List<Log>) {


        feeeling1 = logList.count{
            it.feeling == 1
        }.toFloat()

        feeeling2 = logList.count{
            it.feeling == 2
        }.toFloat()

        feeeling3 = logList.count{
            it.feeling == 3
        }.toFloat()

        feeeling4 = logList.count{
            it.feeling == 4
        }.toFloat()

        feeeling5 = logList.count{
            it.feeling == 5
        }.toFloat()

        feeeling6 = logList.count{
            it.feeling == 6
        }.toFloat()

        feeeling7 = logList.count{
            it.feeling == 7
        }.toFloat()

        feeeling8 = logList.count{
            it.feeling == 8
        }.toFloat()

        feeeling9 = logList.count{
            it.feeling == 9
        }.toFloat()

        feeeling10 = logList.count{
            it.feeling == 10
        }.toFloat()

    }


}