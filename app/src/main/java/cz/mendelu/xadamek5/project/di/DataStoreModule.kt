package cz.mendelu.xadamek5.project.di

import android.content.Context
import cz.mendelu.xadamek5.project.datastore.DataStoreRepositoryImpl
import cz.mendelu.xadamek5.project.datastore.IDataStoreRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { provideDataStoreRepository(androidContext()) }
}

fun provideDataStoreRepository(context: Context): IDataStoreRepository
        = DataStoreRepositoryImpl(context)