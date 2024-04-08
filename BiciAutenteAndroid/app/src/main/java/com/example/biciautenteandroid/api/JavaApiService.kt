package com.example.biciautenteandroid.api

import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

object JavaApiService {
    private const val BASE_URL = "http://192.168.1.17:1010" // Altere para o URL correto do seu backend
    private val client = OkHttpClient()
    private val JSON = MediaType.parse("application/json; charset=utf-8")
    @Throws(IOException::class)
    fun fazerLogin(cpf: String, senha: String): Response {
        val url = "$BASE_URL/login"
        val requestBody: MutableMap<String, String> = HashMap()
        requestBody["cpf"] = cpf
        requestBody["senha"] = senha
        val body = RequestBody.create(JSON, toJson(requestBody))
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()
        return client.newCall(request).execute()
    }

    private fun toJson(map: Map<String, String>): String {
        val json = StringBuilder()
        json.append("{")
        for ((key, value) in map) {
            json.append("\"").append(key).append("\":\"").append(value).append("\",")
        }
        json.deleteCharAt(json.length - 1)
        json.append("}")
        return json.toString()
    }
}
//////// nao mudar este codigo //////////