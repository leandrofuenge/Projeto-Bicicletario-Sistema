package com.example.biciautenteandroid.api

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.io.IOException

// Defina o URL base da API Java que você deseja consumir
private const val BASE_URL = "http://192.168.1.17:1010/login"

// Interface que define as chamadas para a API Java
interface JavaApiService {
    @POST("login")
    suspend fun fazerLogin(@Body requestBody: Map<String, String>): Response<Usuario>
}

data class Usuario(
    val id: Long,
    val nome: String,
    val cpf: String,
    // Adicione outras propriedades conforme necessário
)

// Função para criar uma instância do serviço Retrofit
fun createJavaApiService(): JavaApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(JavaApiService::class.java)
}


// Função para exibir uma mensagem de sucesso na tela
fun mostrarMensagemSucesso(context: Context, mensagem: String) {
    Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show()
}

// Função para exibir uma mensagem de erro na tela
fun mostrarMensagemErro(view: View, mensagem: String) {
    Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT).show()
}


// Exemplo de como usar o serviço para fazer uma chamada à API Java
suspend fun fazerLoginNaApiJava(context: Context, cpf: String, senha: String, view: View) {
    val javaApiService = createJavaApiService()
    val requestBody = mapOf("cpf" to cpf, "senha" to senha)

    try {
        val response = javaApiService.fazerLogin(requestBody)
        if (response.isSuccessful) {
            val usuario = response.body()
            // Faça algo com os dados do usuário recebidos da API Java
            mostrarMensagemSucesso(context, "Login realizado com sucesso")
        } else {
            val errorBody = response.errorBody()?.string()
            // Trate a resposta de erro da API Java
            mostrarMensagemErro(view, "Erro ao fazer login: $errorBody")
        }
    } catch (e: IOException) {
        // Trate exceções de IO, como falhas de conexão de rede
        mostrarMensagemErro(view, "Erro de conexão: ${e.message}")
    } catch (e: Exception) {
        // Trate outras exceções que possam ocorrer durante a solicitação
        mostrarMensagemErro(view, "Erro inesperado: ${e.message}")
    }
}
