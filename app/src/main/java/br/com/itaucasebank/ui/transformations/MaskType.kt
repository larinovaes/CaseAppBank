package br.com.itaucasebank.ui.transformations

enum class MaskType(val mask: String) {
    CPF_MASK("###.###.###-##"),
    ACCOUNT_NUMBER_MASK("#####-#");
}
