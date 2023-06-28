package cz.mendelu.xadamek5.project.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import cz.mendelu.xadamek5.project.ui.activities.states.SplashScreenUiState
import cz.mendelu.xadamek5.project.ui.activities.viewmodels.SplashScreenViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")

class SplashScreenActivity : AppCompatActivity() {

    private val viewModel: SplashScreenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }

        lifecycleScope.launchWhenResumed {

            viewModel.splashScreenState.collect { value ->

                when (value) {
                    is SplashScreenUiState.Default -> {
                        viewModel.checkAppState()
                    }
                    SplashScreenUiState.ContinueToApp -> {
                        continueToMain()
                    }
                    is SplashScreenUiState.RunForAFirstTime -> {
                        runCreateProfile()
                    }
                }


            }
        }

    }

    private fun runCreateProfile(){
        startActivity(CreateProfileActivity.createIntent(this))
        finish()
    }

    private fun continueToMain(){
        startActivity(MainActivity.createIntent(this))
        finish()
    }

}


