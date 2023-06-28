package cz.mendelu.xadamek5.project.di


import cz.mendelu.xadamek5.project.OdvykackaApp
import cz.mendelu.xadamek5.project.database.image.AppDatabase
import org.koin.dsl.module

val databaseModule = module {

    fun provideImageDatabase(): AppDatabase {
        return AppDatabase.getDatabase(OdvykackaApp.appContext)
    }

    single { provideImageDatabase() }

}