package br.com.itaucasebank.enums

import androidx.annotation.StringRes
import br.com.itaucasebank.R

enum class BankType(
    val code: String,
    @StringRes val description: Int,
) {
    BB(
        code = "123",
        description = R.string.bank_type_brazil,
    ),
    NUBANK(
        code = "222",
        description = R.string.bank_type_nubank,
    ),
    ITAU(
        code = "342",
        description = R.string.bank_type_itau,
    ),
    PAN(
        code = "221",
        description = R.string.bank_type_pan,
    ),
    ORIGINAL(
        code = "225",
        description = R.string.bank_type_original,
    )
}
