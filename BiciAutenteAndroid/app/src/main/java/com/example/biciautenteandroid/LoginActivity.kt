package com.example.biciautenteandroid

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

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
        // Aqui você pode adicionar a lógica para validar o login com o banco de dados ou outro método de autenticação
        // Por enquanto, vamos considerar que o login é válido se o CPF e a senha não estiverem vazios
        return cpf.isNotBlank() && senha.isNotBlank()
    }
}
