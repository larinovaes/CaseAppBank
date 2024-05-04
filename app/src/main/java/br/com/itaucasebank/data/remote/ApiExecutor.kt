package br.com.itaucasebank.data.remote

import retrofit2.Response

class ApiExecutor {

    suspend fun <T> executeRequest(request: suspend () -> Response<T>): Result<T> {
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
