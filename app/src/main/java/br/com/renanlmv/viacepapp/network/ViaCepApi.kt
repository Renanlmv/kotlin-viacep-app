package br.com.renanlmv.viacepapp.network

import br.com.renanlmv.viacepapp.model.CepResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepApi {

    @GET("{cep}/json/")
    suspend fun buscarCep(@Path ("cep") cep: String): CepResponse
}