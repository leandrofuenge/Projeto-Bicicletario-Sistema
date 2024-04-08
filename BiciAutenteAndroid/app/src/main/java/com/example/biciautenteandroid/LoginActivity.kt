package com.example.biciautenteandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.biciautenteandroid.api.JavaApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            val cpf = editTextUsername.text.toString()
            val senha = editTextPassword.text.toString()

            if (validarLogin(cpf, senha)) {
                Toast.makeText(
                    this,
                    "Login realizado com sucesso!",
                    Toast.LENGTH_SHORT
                ).show()
                // Aqui você pode adicionar o código para redirecionar o usuário para a próxima tela ou executar outras ações após o login bem-sucedido
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Finaliza a atividade atual para impedir que o usuário retorne à tela de login pressionando o botão Voltar
            } else {
                Toast.makeText(
                    this,
                    "CPF ou senha incorretos. Tente novamente.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun validarLogin(cpf: String, senha: String): Boolean {
        // Verifica se o CPF e a senha não estão vazios
        if (cpf.isBlank() || senha.isBlank()) {
            return false
        }

        // Aqui você pode fazer a chamada à API para verificar se o login é válido
        CoroutineScope(Dispatchers.Main).launch {
            val response = JavaApiService.fazerLogin(cpf, senha)

            // Se o login for bem-sucedido, inicia a MainActivity
            if (response.isSuccessful) {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish() // Finaliza a atividade atual para impedir que o usuário retorne à tela de login pressionando o botão Voltar
            } else {
                // O login é inválido se a resposta da API indicar falha
                Toast.makeText(
                    this@LoginActivity,
                    "CPF ou senha incorretos. Tente novamente.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return true // Retorne true imediatamente, a validação será concluída assincronamente
    }
}