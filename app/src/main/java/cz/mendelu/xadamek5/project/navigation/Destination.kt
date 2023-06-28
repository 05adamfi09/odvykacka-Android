package cz.mendelu.xadamek5.project.navigation

sealed class Destination(val route: String){
    object MainScreen: Destination("main_screen")
    object NewLogScreen: Destination("new_log_screen")
    object LogListScreen: Destination("log_list_screen")
    object LogDetailScreen: Destination("log_detail_screen")
    object SettingsScreen: Destination("settings_screen")
    object UpdateProfileScreen: Destination("update_profile_screen")
}