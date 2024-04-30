package br.com.itaucasebank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.itaucasebank.domain.mapper.toConverter
import br.com.itaucasebank.domain.model.PaymentModel
import br.com.itaucasebank.domain.usecase.GetPaymentModelsUseCase
import br.com.itaucasebank.presentation.uistate.ExtractUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExtractViewModel(
    private val getPaymentModelsUseCase: GetPaymentModelsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    init {
        getExtracts()
    }

    fun getExtracts() = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true, isError = false) }
        getPaymentModelsUseCase.execute()
            .onSuccess { handleSuccess(it) }
            .onFailure { _uiState.update { it.copy(isLoading = false, isError = true) } }
    }

    private fun handleSuccess(paymentModels: List<PaymentModel>) {
        val extractUIStateList = paymentModels.toConverter()
        _uiState.update { it.copy(isLoading = false, extractList = extractUIStateList) }
    }

    data class UIState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val extractList: List<ExtractUIState> = emptyList(),
    )
}