package cz.mendelu.xadamek5.project.database.image

import cz.mendelu.xadamek5.project.model.Log
import cz.mendelu.xadamek5.project.model.Profile
import kotlinx.coroutines.flow.Flow

class AppRepositoryImpl(private val appDao: AppDao) : IAppRepository {
    override suspend fun saveProfile(profile: Profile) {
        return appDao.saveProfile(profile)
    }

    override suspend fun getProfile(): Profile {
        return appDao.getProfile()
    }

    override suspend fun updateProfile(profile: Profile) {
        return appDao.updateProfile(profile)
    }


    override suspend fun deleteProfile() {
        return appDao.deleteProfile()
    }

    override suspend fun insert(log: Log): Long {
        return appDao.insertLog(log)
    }

    override suspend fun update(log: Log) {
        return appDao.updateLog(log)
    }

    override fun getAll(): Flow<List<Log>> {
        return appDao.getAll()
    }

    override suspend fun findById(id: Long): Log {
        return appDao.findById(id)
    }

    override suspend fun getAllInList(): List<Log> {
        return appDao.getAllInList()
    }

    override suspend fun deleteLog(log: Log) {
        return appDao.deleteLog(log)
    }
}