package br.com.itaucasebank.di

import br.com.itaucasebank.presentation.viewmodel.HomeViewModel
import br.com.itaucasebank.presentation.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val appModule = module {
    viewModel { LoginViewModel() }
    viewModel { HomeViewModel() }
}

private val domainModule = module {

}

private val dataModule = module {

}

val allModules = appModule + domainModule + dataModule
