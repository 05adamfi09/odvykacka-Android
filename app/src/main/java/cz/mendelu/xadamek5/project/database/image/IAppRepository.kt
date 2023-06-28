package cz.mendelu.xadamek5.project.database.image

import cz.mendelu.xadamek5.project.model.Log
import cz.mendelu.xadamek5.project.model.Profile
import kotlinx.coroutines.flow.Flow

interface IAppRepository {

    suspend fun saveProfile(profile: Profile)

    suspend fun getProfile(): Profile
    suspend fun updateProfile(profile: Profile)



    suspend fun deleteProfile()

    suspend fun insert(log: Log): Long
    suspend fun update(log: Log)
    fun getAll(): Flow<List<Log>>
    suspend fun findById(id : Long): Log

    suspend fun getAllInList(): List<Log>

    suspend fun deleteLog(log: Log)



}