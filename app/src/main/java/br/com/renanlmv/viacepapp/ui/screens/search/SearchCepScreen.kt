package br.com.renanlmv.viacepapp.ui.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.com.renanlmv.viacepapp.core.UiState

@Composable
fun SearchCepScreen( searchViewModel: SearchViewModel ) {
    val cep = searchViewModel.cep
    val uiState = searchViewModel.uiState

    Scaffold() { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Digite um CEP para buscar o endereço",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = searchViewModel.cep,
                    onValueChange = {newValue -> searchViewModel.onCepChange(newValue)},
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { searchViewModel.searchCep() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Buscar cep")
                }
                Spacer(modifier = Modifier.height(24.dp))

                when(uiState) {
                    is UiState.Error -> Text(uiState.message)
                    is UiState.Initial -> {}
                    is UiState.Loading -> CircularProgressIndicator()
                    is UiState.Success -> Text(uiState.data.logradouro ?: "")
                }
            }
        }
    }
}