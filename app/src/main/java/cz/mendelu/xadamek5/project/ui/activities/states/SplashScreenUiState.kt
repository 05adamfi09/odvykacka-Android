package cz.mendelu.xadamek5.project.ui.activities.states

sealed class SplashScreenUiState {
    object Default : SplashScreenUiState()
    object RunForAFirstTime : SplashScreenUiState()
    object ContinueToApp : SplashScreenUiState()
}
