package br.com.renanlmv.viacepapp.model

data class CepResponse (
    val cep: String?,
    val logradouro: String?,
    val bairro: String?,
    val estado: String?,
    val uf: String?
)