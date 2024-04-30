package br.com.itaucasebank.di.provider

import br.com.itaucasebank.data.local.AppDatabase

object DaoProvider {

    fun provideAppDao(appDatabase: AppDatabase) = appDatabase.getUserDao()
}
