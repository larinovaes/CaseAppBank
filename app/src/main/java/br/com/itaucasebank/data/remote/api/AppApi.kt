package br.com.itaucasebank.data.remote.api

import br.com.itaucasebank.data.remote.dto.AccountDetailsDto
import br.com.itaucasebank.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AppApi {
    @GET("/api/v1/user")
    suspend fun getUsers(): Response<List<UserDto>>

    @GET("/api/v1/user/{id}/account")
    suspend fun getAccounts(@Path("id") userId: String): Response<List<AccountDetailsDto>>
}