package cz.mendelu.xadamek5.project.ui.activities.viewmodels

import cz.mendelu.xadamek5.project.architecture.BaseViewModel
import cz.mendelu.xadamek5.project.database.image.IAppRepository
import cz.mendelu.xadamek5.project.datastore.IDataStoreRepository
import cz.mendelu.xadamek5.project.model.Profile
import kotlinx.coroutines.launch

class CreateProfileActivityViewModel(private val dataStoreRepository: IDataStoreRepository, private val repository: IAppRepository) : BaseViewModel() {

    suspend fun setFirstRun(){
        dataStoreRepository.setFirstRun()

    }

    fun saveProfile(profile: Profile) {
        launch {
            repository.saveProfile(profile)
        }
    }

}