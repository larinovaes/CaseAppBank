package br.com.itaucasebank.di.modules

import br.com.itaucasebank.data.infrastructure.ApiExecutor
import br.com.itaucasebank.data.local.AppDatabase
import br.com.itaucasebank.data.repository.AppRepository
import br.com.itaucasebank.di.provider.ApiProvider
import br.com.itaucasebank.di.provider.DaoProvider
import br.com.itaucasebank.di.provider.NetworkProvider
import br.com.itaucasebank.domain.usecase.AuthenticateUserUseCase
import br.com.itaucasebank.domain.usecase.GetPaymentModelsUseCase
import br.com.itaucasebank.domain.usecase.GetUserAndAccountDetailsUseCase
import br.com.itaucasebank.presentation.viewmodel.ExtractViewModel
import br.com.itaucasebank.presentation.viewmodel.HomeViewModel
import br.com.itaucasebank.presentation.viewmodel.LoginViewModel
import br.com.itaucasebank.presentation.viewmodel.TransferSharedViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val appModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { ExtractViewModel(get()) }
    viewModel { TransferSharedViewModel(androidApplication()) }
}

private val domainModule = module {
    factory { AuthenticateUserUseCase(get()) }
    factory { GetUserAndAccountDetailsUseCase(get()) }
    factory { GetPaymentModelsUseCase(get()) }
}

private val dataModule = module {
    factory { ApiExecutor() }
    factory { AppRepository(get(), get(), get()) }
    single { NetworkProvider.provideRetrofit() }
    single { ApiProvider.provideAppApi(get()) }
    single { AppDatabase.getDataBase(androidContext()) }
    single { DaoProvider.provideAppDao(get()) }
}

val allModules = appModule + domainModule + dataModule
