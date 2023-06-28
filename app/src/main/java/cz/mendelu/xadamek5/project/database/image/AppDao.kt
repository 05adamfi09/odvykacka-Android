package cz.mendelu.xadamek5.project.database.image

import androidx.room.*
import cz.mendelu.xadamek5.project.model.Log
import cz.mendelu.xadamek5.project.model.Profile
import kotlinx.coroutines.flow.Flow
@Dao
interface AppDao {
    @Insert
    suspend fun saveProfile(profile: Profile)

    @Query("SELECT * FROM profiles WHERE id = 1")
    suspend fun getProfile(): Profile

    @Update
    suspend fun updateProfile(profile: Profile)

    @Query("DELETE FROM profiles WHERE id = 0")
    suspend fun deleteProfile()

    @Insert
    suspend fun insertLog(log: Log): Long

    @Update
    suspend fun updateLog(log: Log)


    @Query("SELECT * FROM logs")
    fun getAll(): Flow<List<Log>>

    @Query("SELECT * FROM logs WHERE id = :id")
    suspend fun findById(id : Long): Log

    @Query("SELECT * FROM logs")
    suspend fun getAllInList(): List<Log>

    @Delete
    suspend fun deleteLog(log: Log)

}