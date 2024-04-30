package br.com.itaucasebank.data.repository

import br.com.itaucasebank.data.remote.api.AppApi
import br.com.itaucasebank.domain.AccountDetailsModel
import br.com.itaucasebank.presentation.extension.toModel
import retrofit2.Response

class Repository(private val appApi: AppApi) {

    suspend fun getPaymentStatement(userId: String): Result<List<AccountDetailsModel>> {
        return executeRequest { appApi.getPaymentStatement(userId) }
            .map { it.toModel() }
    }

    private suspend fun <T> executeRequest(request: suspend () -> Response<T>): Result<T> {
        return try {
            val result = request()
            if (result.isSuccessful && result.body() != null) {
                return Result.success(requireNotNull(result.body()))
            } else {
                Result.failure(Exception("Api request failed"))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}