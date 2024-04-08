package com.example.biciautenteandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.biciautenteandroid.api.fazerLoginNaApiJava
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var cpf: String
    private lateinit var senha: String

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            // Obtendo valores dos campos de texto
            cpf = editTextUsername.text.toString()
            senha = editTextPassword.text.toString()

            GlobalScope.launch(Dispatchers.Main) {
                try {
                    // Fazer login na API
                    val response = fazerLoginNaApiJava(applicationContext, cpf, senha)

                    if (response.successful) {
                        // Se o login for bem-sucedido, iniciar a MainActivity
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)

                        // Finalizar a LoginActivity para evitar que o usuário volte para ela usando o botão "Voltar"
                        finish()
                    } else {
                        // Se o login falhar, exibir mensagem de erro
                        Toast.makeText(
                            applicationContext,
                            response.errorMessage ?: "Erro ao fazer login",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    // Trate a exceção, se necessário
                }
            }
        }
    }
}
