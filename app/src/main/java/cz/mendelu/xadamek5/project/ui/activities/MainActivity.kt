package cz.mendelu.xadamek5.project.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cz.mendelu.xadamek5.project.navigation.Destination
import cz.mendelu.xadamek5.project.navigation.NavGraph
import cz.mendelu.xadamek5.project.ui.theme.ProjectTheme
import cz.mendelu.xadamek5.project.architecture.BaseActivity
import cz.mendelu.xadamek5.project.ui.activities.viewmodels.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainActivityViewModel>(MainActivityViewModel::class.java) {

    override val viewModel: MainActivityViewModel by viewModel()

    companion object {
        fun createIntent(context: AppCompatActivity): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            ProjectTheme {
                // jen spustím NavGraph, nic víc.
                NavGraph(startDestination = Destination.MainScreen.route)
            }
        }
    }
}