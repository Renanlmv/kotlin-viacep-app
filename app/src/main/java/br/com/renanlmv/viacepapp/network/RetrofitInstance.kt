package br.com.renanlmv.viacepapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// object é uma classe semelhante ao static no Java
object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://viacep.com.br/ws/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(ViaCepApi::class.java)
}