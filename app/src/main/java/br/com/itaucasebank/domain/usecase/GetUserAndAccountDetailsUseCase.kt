package br.com.itaucasebank.domain.usecase

import br.com.itaucasebank.data.repository.AppRepository
import br.com.itaucasebank.domain.model.UserAndAccountDetailsModel

class GetUserAndAccountDetailsUseCase(private val appRepository: AppRepository) {

    suspend fun execute(): Result<UserAndAccountDetailsModel> {
        val userModel = appRepository.readUser()
        val userAccount = appRepository
            .getAccounts(userModel.id)
            .getOrNull()
            ?.firstOrNull()
            ?: return Result.failure(Exception("Could not retrieve user account details"))
        val userAndAccountDetailsModel = UserAndAccountDetailsModel(
            userName = userModel.name,
            profileImageUrl = userModel.avatar,
            accountNumber = userAccount.account,
            agencyNumber = "342",
            balanceValue = userAccount.amount,
        )
        return Result.success(userAndAccountDetailsModel)
    }
}
