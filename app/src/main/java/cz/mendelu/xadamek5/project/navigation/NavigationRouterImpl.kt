package cz.mendelu.xadamek5.project.navigation

import androidx.navigation.NavController

class NavigationRouterImpl(private val navController: NavController) : INavigationRouter {
    override fun returnBack() {
        navController.popBackStack()
    }

    override fun navigateToNewLogScreen(id: Long?) {
        navController.navigate(Destination.NewLogScreen.route + "/" + id)
    }

    override fun getNavController(): NavController {
        return navController
    }


    override fun navigateToMainScreen() {
        navController.navigate(Destination.MainScreen.route)
    }

    override fun navigateToLogListScreen() {
        navController.navigate(Destination.LogListScreen.route)
    }

    override fun navigateToLogDetailScreen(id: Long?) {
        navController.navigate(Destination.LogDetailScreen.route + "/" + id)
    }

    override fun navigateToSettingsScreen() {
        navController.navigate(Destination.SettingsScreen.route)
    }

    override fun navigateToUpdateProfileScreen() {
        navController.navigate(Destination.UpdateProfileScreen.route)
    }


}