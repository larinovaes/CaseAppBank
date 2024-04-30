package br.com.itaucasebank.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.com.itaucasebank.R

enum class Menu(
    @DrawableRes val icon: Int,
    @StringRes val description: Int,
) {
    HOME(icon = R.drawable.ic_home, description = R.string.menu_home),
    SEARCH(icon = R.drawable.ic_search, description = R.string.menu_search),
    EMAIL(icon = R.drawable.ic_email, description = R.string.menu_email),
    SETTINGS(icon = R.drawable.ic_settings, description = R.string.menu_settings),
}