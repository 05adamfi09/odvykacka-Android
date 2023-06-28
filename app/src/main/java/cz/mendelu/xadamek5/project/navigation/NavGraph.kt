package cz.mendelu.xadamek5.project.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cz.mendelu.xadamek5.project.ui.screens.LogDetailScreen.LogDetailScreen
import cz.mendelu.xadamek5.project.ui.screens.settingsScreen.SettingsScreen
import cz.mendelu.xadamek5.project.ui.screens.logListScreen.LogListScreen
import cz.mendelu.xadamek5.project.ui.screens.mainScreen.MainScreen
import cz.mendelu.xadamek5.project.ui.screens.newLog.NewLogScreen
import cz.mendelu.xadamek5.project.ui.screens.updateProfileScreen.UpdateProfileScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    navigation: INavigationRouter = remember {
        NavigationRouterImpl(navController)
    },
    startDestination: String
) {


    NavHost(navController = navController, startDestination = startDestination){

        composable(route = Destination.MainScreen.route){
            MainScreen(navigation)
        }

        composable(route = Destination.NewLogScreen.route + "/{id}",
        arguments = listOf(
            navArgument("id") {
                type = NavType.LongType
                defaultValue = -1L
            }
        )
        ){
            val id = it.arguments?.getLong("id")
            NewLogScreen(navigation = navigation, id = if (id != -1L) id else null)
        }


        composable(route = Destination.LogListScreen.route){
            LogListScreen(navigation = navigation)
        }

        composable(route = Destination.LogDetailScreen.route + "/{id}",
        arguments = listOf(
            navArgument("id") {
                type = NavType.LongType
                defaultValue = -1L
            }
        )
        ){
            val id = it.arguments?.getLong("id")
            LogDetailScreen(navigation = navigation, id = if (id != -1L) id else null)
        }

        composable(route = Destination.SettingsScreen.route){
            SettingsScreen(navigation = navigation)
        }

        composable(route = Destination.UpdateProfileScreen.route){
            UpdateProfileScreen(navigation = navigation)
        }


    }


}