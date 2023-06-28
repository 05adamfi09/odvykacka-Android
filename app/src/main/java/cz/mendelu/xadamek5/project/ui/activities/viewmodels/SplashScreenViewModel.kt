package cz.mendelu.xadamek5.project.ui.activities.viewmodels

import cz.mendelu.xadamek5.project.architecture.BaseViewModel
import cz.mendelu.xadamek5.project.datastore.IDataStoreRepository
import cz.mendelu.xadamek5.project.ui.activities.states.SplashScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel(private val dataStoreRepository: IDataStoreRepository) : BaseViewModel() {

    private val _splashScreenState = MutableStateFlow<SplashScreenUiState>(SplashScreenUiState.Default)
    val splashScreenState: StateFlow<SplashScreenUiState> = _splashScreenState

    fun checkAppState(){
        launch {

            if (dataStoreRepository.getFirstRun()){
                _splashScreenState.value = SplashScreenUiState.RunForAFirstTime
            } else {
                _splashScreenState.value = SplashScreenUiState.ContinueToApp
            }

        }
    }

}