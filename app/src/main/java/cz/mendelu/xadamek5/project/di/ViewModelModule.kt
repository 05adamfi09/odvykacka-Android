package cz.mendelu.xadamek5.project.di


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import cz.mendelu.xadamek5.project.ui.activities.viewmodels.CreateProfileActivityViewModel
import cz.mendelu.xadamek5.project.ui.activities.viewmodels.MainActivityViewModel
import cz.mendelu.xadamek5.project.ui.activities.viewmodels.SplashScreenViewModel
import cz.mendelu.xadamek5.project.ui.screens.LogDetailScreen.LogDetailScreenViewModel
import cz.mendelu.xadamek5.project.ui.screens.settingsScreen.SettingsScreenViewModel
import cz.mendelu.xadamek5.project.ui.screens.logListScreen.LogListScreenViewModel
import cz.mendelu.xadamek5.project.ui.screens.mainScreen.MainScreenViewModel
import cz.mendelu.xadamek5.project.ui.screens.newLog.NewLogScreenViewModel
import cz.mendelu.xadamek5.project.ui.screens.updateProfileScreen.UpdateProfileScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.O)
val viewModelModule = module {
    // todo viewModuly
    viewModel { MainScreenViewModel(get()) }
    viewModel { NewLogScreenViewModel(get(), get()) }
    viewModel { LogListScreenViewModel(get())}
    viewModel { LogDetailScreenViewModel(get())}
    viewModel { SettingsScreenViewModel(get())}
    viewModel { UpdateProfileScreenViewModel(get())}

    viewModel { SplashScreenViewModel(get()) }
    viewModel { MainActivityViewModel() }
    viewModel { CreateProfileActivityViewModel(get(), get()) }

    fun provideSavedStateHandle(): SavedStateHandle {
        return SavedStateHandle()
    }

    factory { provideSavedStateHandle() }

}