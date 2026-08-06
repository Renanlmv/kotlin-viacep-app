package br.com.renanlmv.viacepapp.ui.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.renanlmv.viacepapp.core.UiState
import br.com.renanlmv.viacepapp.model.CepResponse
import br.com.renanlmv.viacepapp.network.RetrofitInstance
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    var cep by mutableStateOf("")
        private set // significa que o atributo acima só pode ser alterado pela ViewModel

    var uiState by mutableStateOf<UiState<CepResponse>>(UiState.Initial)
        private set

    fun onCepChange(newValue: String) {
        cep = newValue.filter { it.isDigit() }.take(8)
        if(uiState is UiState.Error) {
            uiState = UiState.Initial
        }
    }

    fun searchCep() {
        // quando pesquisar, vai pra tela de Loading
        uiState = UiState.Loading

        val cepClear = cep.filter { it.isDigit() }
        if(cepClear.length != 8) {
            uiState = UiState.Error("Cep inválido")
            return
        }

        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.buscarCep(cepClear)
                uiState = UiState.Success(response)
            } catch (e: Exception) {
                uiState = UiState.Error(e.message.toString())
            }
        }
    }
}