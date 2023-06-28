package cz.mendelu.xadamek5.project.navigation

import androidx.navigation.NavController

interface INavigationRouter {
    fun returnBack()
    fun navigateToNewLogScreen(id: Long?)
    fun getNavController(): NavController
    fun navigateToMainScreen()
    fun navigateToLogListScreen()
    fun navigateToLogDetailScreen(id: Long?)
    fun navigateToSettingsScreen()
    fun navigateToUpdateProfileScreen()
}