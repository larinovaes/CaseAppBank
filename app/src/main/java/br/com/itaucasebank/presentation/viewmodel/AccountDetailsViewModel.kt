package br.com.itaucasebank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.itaucasebank.data.repository.Repository
import br.com.itaucasebank.domain.AccountDetailsModel
import br.com.itaucasebank.presentation.extension.toConverter
import br.com.itaucasebank.presentation.uistate.ExtractUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AccountDetailsViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    init {
        getPaymentStatement("1")
    }

    fun getPaymentStatement(userId: String) = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true, isError = false) }
        repository.runCatching {
            getPaymentStatement(userId)
                .onSuccess { handleSuccess(it) }
                .onFailure {_uiState.update { it.copy(isLoading = false, isError = true) } }
        }
    }

    private fun handleSuccess(accountDetailsModels: List<AccountDetailsModel>) {
        val extractUIStateList = accountDetailsModels
            .map { it.payments.toConverter() }
            .flatten()
        _uiState.update { it.copy(isLoading = false, extractList = extractUIStateList) }
    }

    data class UIState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val extractList: List<ExtractUIState> = emptyList(),
    )
}