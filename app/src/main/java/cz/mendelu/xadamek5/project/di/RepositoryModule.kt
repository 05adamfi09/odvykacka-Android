package cz.mendelu.xadamek5.project.di

import cz.mendelu.xadamek5.project.database.image.AppDao
import cz.mendelu.xadamek5.project.database.image.IAppRepository
import cz.mendelu.xadamek5.project.database.image.AppRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    fun provideImageRepository(dao: AppDao): IAppRepository {
        return AppRepositoryImpl(dao)
    }

    single { provideImageRepository(get()) }


}