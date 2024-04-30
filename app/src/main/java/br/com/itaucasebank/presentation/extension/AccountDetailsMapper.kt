package br.com.itaucasebank.presentation.extension

import br.com.itaucasebank.data.remote.dto.AccountDetailsDto
import br.com.itaucasebank.domain.AccountDetailsModel
import br.com.itaucasebank.domain.PaymentModel
import br.com.itaucasebank.presentation.uistate.ExtractUIState

fun List<AccountDetailsDto>.toModel(): List<AccountDetailsModel> {
    val accountDetailsModel = this.map { it ->
        AccountDetailsModel(
            id = it.id,
            userId = it.userId,
            createdAt = it.createdAt,
            account = it.account,
            amount = it.amount,
            payments = it.payments.map {
                PaymentModel(
                    id = it.id,
                    name = it.name,
                    category = it.category,
                    createdAt = it.createdAt,
                    amount = it.amount
                )
            }
        )
    }
    return accountDetailsModel
}

fun List<PaymentModel>.toConverter(): List<ExtractUIState> {
    val extractUIState = this.map {
        ExtractUIState(
            id = it.id,
            name = it.name,
            category = it.category,
            createdAt = it.createdAt,
            amount = it.amount,
        )
    }
    return extractUIState
}