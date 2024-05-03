package br.com.itaucasebank.enums

import androidx.annotation.StringRes
import br.com.itaucasebank.R

enum class TransactionType(@StringRes val title: Int) {
    PIX(title = R.string.extract_screen_text_pix),
    DOC(title = R.string.extract_screen_text_doc),
    TED(title = R.string.extract_screen_text_ted),
    NONE(title = R.string.extract_screen_text_none),
}