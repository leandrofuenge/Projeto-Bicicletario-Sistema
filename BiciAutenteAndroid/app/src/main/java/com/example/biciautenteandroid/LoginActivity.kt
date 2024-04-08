package com.example.biciautenteandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.biciautenteandroid.api.ApiResponse
import com.example.biciautenteandroid.api.ApiService
import com.example.biciautenteandroid.api.LoginData
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.17:1010/") // Altere para o URL correto
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            val cpf = editTextUsername.text.toString()
            val senha = editTextPassword.text.toString()

            coroutineScope.launch {
                try {
                    val response = fazerLogin(cpf, senha)

                    if (response.successful) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Login realizado com sucesso",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            response.errorMessage ?: "Erro ao fazer login",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    // Trate a exceção, se necessário
                    Toast.makeText(
                        this@LoginActivity,
                        "Erro ao fazer login: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel() // Cancela todas as corrotinas quando a atividade é destruída
    }

    private suspend fun fazerLogin(cpf: String, senha: String): ApiResponse {
        return withContext(Dispatchers.IO) {
            val loginData = LoginData(cpf, senha)
            apiService.fazerLogin(loginData)
        }
    }
}
