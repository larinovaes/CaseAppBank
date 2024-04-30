package br.com.itaucasebank.domain.usecase

import br.com.itaucasebank.data.repository.AppRepository
import br.com.itaucasebank.domain.model.PaymentModel

class GetPaymentModelsUseCase(private val appRepository: AppRepository) {

    suspend fun execute(): Result<List<PaymentModel>> {
        val authenticatedUser = appRepository.readUser()
        val accountPayments = appRepository
            .getAccounts(authenticatedUser.id)
            .getOrNull()
            ?.map { it.payments }
            ?.flatten() ?: return Result.failure(Exception("Could not retrieve account payments"))
        return Result.success(accountPayments)
    }
}
