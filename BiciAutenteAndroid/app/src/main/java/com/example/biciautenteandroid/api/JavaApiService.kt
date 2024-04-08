package com.example.biciautenteandroid.api

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.biciautenteandroid.MainActivity
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import java.io.IOException

import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


// Defina o URL base da API Java que você deseja consumir
private const val BASE_URL = "http://192.168.1.17:1010"

// Data class para os dados de login
data class LoginData(
    val cpf: String,
    val senha: String
)

// Data class para a resposta da API
data class ApiResponse(
    val successful: Boolean,
    val errorMessage: String? = null
)

// Interface para definir os endpoints da API
interface ApiService {
    @POST("/login")
    suspend fun fazerLogin(@Body loginData: LoginData): ApiResponse
}

// Função para realizar o login na API Java usando Ktor
suspend fun fazerLoginNaApiJava(context: Context, cpf: String, senha: String): ApiResponse {
    val client = HttpClient(Android) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }

    val apiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    val loginData = LoginData(cpf, senha)

    try {
        val response = apiService.fazerLogin(loginData)

        if (response.successful) {
            // Login bem-sucedido, mostra a mensagem de sucesso
            Toast.makeText(context, "Login realizado com sucesso", Toast.LENGTH_SHORT).show()

            // Inicia a MainActivity após o login bem-sucedido
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        } else {
            // Trate a resposta de erro da API Java
            Toast.makeText(context, "Erro ao fazer login: ${response.errorMessage}", Toast.LENGTH_SHORT).show()
        }
        return response
    } catch (e: IOException) {
        // Trate exceções de IO, como falhas de conexão de rede
        Toast.makeText(context, "Erro de conexão: ${e.message}", Toast.LENGTH_SHORT).show()
        throw e
    } catch (e: Exception) {
        // Trate outras exceções que possam ocorrer durante a solicitação
        Toast.makeText(context, "Erro inesperado: ${e.message}", Toast.LENGTH_SHORT).show()
        throw e
    } finally {
        client.close()
    }
}