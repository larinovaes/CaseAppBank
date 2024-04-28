package br.com.itaucasebank.enums

import androidx.annotation.DrawableRes
import br.com.itaucasebank.R

enum class MenuCard(@DrawableRes val icon: Int, val title: String) {
    NEW_TRANSFER(icon = R.drawable.ic_icon_bank, title = "Nova Transferência"),
}
