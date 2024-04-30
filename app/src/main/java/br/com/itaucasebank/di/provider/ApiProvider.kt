package br.com.itaucasebank.di.provider

import br.com.itaucasebank.data.remote.api.AppApi
import retrofit2.Retrofit

object ApiProvider {

    fun provideAppApi(retrofit: Retrofit) = retrofit.create(AppApi::class.java)
}
