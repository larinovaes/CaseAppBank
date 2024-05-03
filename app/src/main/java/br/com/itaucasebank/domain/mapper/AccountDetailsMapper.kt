package br.com.itaucasebank.domain.mapper

import br.com.itaucasebank.core.formatToDateTimePattern
import br.com.itaucasebank.core.formatToCurrency
import br.com.itaucasebank.data.remote.dto.AccountDetailsDto
import br.com.itaucasebank.domain.model.AccountDetailsModel
import br.com.itaucasebank.domain.model.PaymentModel
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
            createdAt = it.createdAt.formatToDateTimePattern(),
            amount = it.amount.formatToCurrency(),
        )
    }
    return extractUIState
}
