package com.example.biciautenteandroid

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.biciautenteandroid.api.fazerLoginNaApiJava
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var cpf: String
    private lateinit var senha: String

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editTextUsername = findViewById<EditText>(R.id.editTextUsername)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            // Obtendo valores dos campos de texto
            cpf = editTextUsername.text.toString()
            senha = editTextPassword.text.toString()

            GlobalScope.launch(Dispatchers.Main) {
                try {
                    fazerLoginNaApiJava(applicationContext, cpf, senha, it)
                } catch (e: Exception) {
                    e.printStackTrace()
                    // Trate a exceção, se necessário
                }
            }
        }
    }
}
