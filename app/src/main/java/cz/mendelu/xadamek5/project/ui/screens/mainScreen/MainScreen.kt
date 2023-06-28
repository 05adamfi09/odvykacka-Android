package cz.mendelu.xadamek5.project.ui.screens.mainScreen

import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import cz.mendelu.xadamek5.project.R
import cz.mendelu.xadamek5.project.model.Log
import cz.mendelu.xadamek5.project.model.Profile
import cz.mendelu.xadamek5.project.navigation.INavigationRouter
import cz.mendelu.xadamek5.project.ui.elements.FilledCircleText
import cz.mendelu.xadamek5.project.ui.elements.MyButton
import cz.mendelu.xadamek5.project.ui.theme.getBasicTextColor
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navigation: INavigationRouter, viewModel: MainScreenViewModel = getViewModel()){

    val logList = remember { mutableStateListOf<Log>() }
    var profile: Profile? = null

    viewModel.mainScreenUIState.value.let {
        when(it){
            is MainScreenUIState.Default -> {
                LaunchedEffect(it){
                    viewModel.load()
                }
            }
            is MainScreenUIState.Success -> {

                    //logList.clear()
                    //logList.addAll(it.logs)
                    viewModel.countDaysFeeling(it.logs)
                    viewModel.countDays(it.logs)
                    profile = it.profile

            }
        }
    }

    Scaffold (
        topBar = {
            TopAppBar(title = { Text(text = "Odvykačka")},
            actions = {
                IconButton(onClick = { navigation.navigateToSettingsScreen()}) {
                    Icon(Icons.Default.Settings, contentDescription = stringResource(R.string.settings))
                }
            }
                )
        }
            ){
        MainScreenContent(paddingValues = it, navigation = navigation, actions = viewModel, logList = logList, viewModel = viewModel, profile = profile)
    }


}


@Composable
fun MainScreenContent(paddingValues: PaddingValues,
                      navigation: INavigationRouter,
                      actions: MainScreenActions,
                      logList: List<Log>,
                      viewModel: MainScreenViewModel,
                      profile: Profile?
                      ){

    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        item {

            Column(verticalArrangement = Arrangement.SpaceEvenly) {

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                    FilledCircleText(
                        text = viewModel.days.toString(),
                        backgroundColor = Color.Blue,
                        textColor = Color.White,
                        size = 150,
                        fontSize = 80
                    )

                }

                Text(stringResource(R.string.days_without) + " " + profile?.addiction,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp)

                Row(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    //Text(text = profile?.nickname ?: "nenalezeno")

                    MyButton(onClick = { navigation.navigateToNewLogScreen(-1L) }, text = stringResource(R.string.new_log))


                    MyButton(onClick = { navigation.navigateToLogListScreen() }, text = stringResource(R.string.my_logs))
                }


            }

        }

        item {


            Spacer(modifier = Modifier.height(100.dp))

            Text(stringResource(R.string.counter_of_yours_feelings), modifier = Modifier
                .fillMaxWidth()
                .size(20.dp), textAlign = TextAlign.Center)

            val textColor = getBasicTextColor().hashCode()

            AndroidView(
                factory = { context ->
                    BarChart(context).apply {


                        setDrawValueAboveBar(true)
                        description.isEnabled = false
                        legend.isEnabled = false
                        axisLeft.isEnabled = false
                        axisRight.isEnabled = false
                        val xAxis = xAxis
                        xAxis.position = XAxis.XAxisPosition.BOTTOM
                        xAxis.setDrawGridLines(false)
                        xAxis.granularity = 1f
                        xAxis.textColor = textColor


                        val entries = listOf(
                            BarEntry(1f, viewModel.feeeling1),
                            BarEntry(2f, viewModel.feeeling2),
                            BarEntry(3f, viewModel.feeeling3),
                            BarEntry(4f, viewModel.feeeling4),
                            BarEntry(5f, viewModel.feeeling5),
                            BarEntry(6f, viewModel.feeeling6),
                            BarEntry(7f, viewModel.feeeling7),
                            BarEntry(8f, viewModel.feeeling8),
                            BarEntry(9f, viewModel.feeeling9),
                            BarEntry(10f, viewModel.feeeling10),
                        )

                        val barDataSet = BarDataSet(entries, "Bar Data Set")
                        barDataSet.valueTextSize = 12f
                        barDataSet.valueFormatter = CustomValueFormatter()
                        barDataSet.valueTextColor = textColor




                        val barData = BarData(barDataSet)
                        data = barData

                        invalidate()
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .height(200.dp)
            )


        }
    }
}

class CustomValueFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        return value.toInt().toString()
    }

}
