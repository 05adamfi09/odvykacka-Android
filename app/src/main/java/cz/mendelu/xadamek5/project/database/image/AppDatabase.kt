package cz.mendelu.xadamek5.project.database.image

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cz.mendelu.xadamek5.project.functions.UriTypeConverter
import cz.mendelu.xadamek5.project.model.Log
import cz.mendelu.xadamek5.project.model.Profile

@Database(entities = [Profile::class, Log::class], version = 11, exportSchema = false)
@TypeConverters(UriTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java, "app_database"
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            }
            return INSTANCE!!
        }


    }

}
