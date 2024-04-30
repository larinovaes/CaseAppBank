package br.com.itaucasebank.di.modules

import br.com.itaucasebank.data.local.AppDatabase
import br.com.itaucasebank.data.repository.Repository
import br.com.itaucasebank.di.provider.ApiProvider
import br.com.itaucasebank.di.provider.DaoProvider
import br.com.itaucasebank.di.provider.NetworkProvider
import br.com.itaucasebank.presentation.viewmodel.AccountDetailsViewModel
import br.com.itaucasebank.presentation.viewmodel.HomeViewModel
import br.com.itaucasebank.presentation.viewmodel.LoginViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val appModule = module {
    viewModel { LoginViewModel() }
    viewModel { HomeViewModel() }
    viewModel { AccountDetailsViewModel(get()) }
}

private val domainModule = module {

}

private val dataModule = module {
    factory { Repository( get()) }
    single { NetworkProvider.provideRetrofit() }
    single { ApiProvider.provideAppApi(get()) }
    single { AppDatabase.getDataBase(androidApplication()) }
    single { DaoProvider.provideAppDao(get()) }
}

val allModules = appModule + domainModule + dataModule
