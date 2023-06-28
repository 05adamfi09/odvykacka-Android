package cz.mendelu.xadamek5.project.di


import cz.mendelu.xadamek5.project.database.image.AppDao
import cz.mendelu.xadamek5.project.database.image.AppDatabase

import org.koin.dsl.module

val daoModule = module {

    fun provideImageDao(imageDatabase: AppDatabase) : AppDao {
        return imageDatabase.appDao()
    }
    single { provideImageDao(get()) }

}