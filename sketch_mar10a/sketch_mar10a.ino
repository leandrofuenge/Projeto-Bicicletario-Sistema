#include <WiFi.h>
#include <MFRC522.h>
#include <SPI.h>
#include <Wire.h>
#include <HTTPClient.h> // Adicionado
#include <LiquidCrystal_I2C.h> // Adicionado

// Definições de pinos e outras variáveis globais
const char* ssid = "LEANDRO";
const char* password = "32867393";
const char* host = "192.168.1.3"; // Endereço IP do servidor Java
const int httpPort = 8080; // Porta HTTP do servidor Java
const char* javaEndpoint = "/autenticar/validacao"; // URL Java

#define SS_PIN 21
#define RST_PIN 22
#define LedVerde 26
#define LedVermelho 12
#define tranca 2
#define buzzer 15

MFRC522::MIFARE_Key key;
MFRC522::StatusCode status;
MFRC522 mfrc522(SS_PIN, RST_PIN);

void setup() {
    Serial.begin(115200);
    SPI.begin();
    mfrc522.PCD_Init();

    Serial.println("Conectando com " + String(ssid));

    WiFi.begin(ssid, password);

    while (WiFi.status() != WL_CONNECTED) {
        delay(500);
        Serial.print(".");
    }

    Serial.println("\nWifi Connectado");
    Serial.println("IP:" + WiFi.localIP().toString());

    pinMode(LedVerde, OUTPUT);
    pinMode(LedVermelho, OUTPUT);
    pinMode(tranca, OUTPUT);
    pinMode(buzzer, OUTPUT);
}

void loop() {
    if (!mfrc522.PICC_IsNewCardPresent())
        return;

    if (!mfrc522.PICC_ReadCardSerial())
        return;

    Serial.println("\nConectando com " + String(host));
    WiFiClient client;

    if (!client.connect(host, httpPort)) {
        Serial.println("Falha na Conexão...");
        return;
    }

    String numeroDoCartao = leituraDados();
    int valorDoPlano = 200; // Exemplo: valor do plano fixo em 200

    String url = String(javaEndpoint) + "?numeroDoCartao=" + numeroDoCartao + "&valorDoPlano=" + String(valorDoPlano);
    Serial.println("Requisitando URL:" + url);

    HTTPClient http;
    http.begin(url); // Iniciar conexão HTTP
    int httpResponseCode = http.GET(); // Fazer requisição GET

    if (httpResponseCode > 0) { // Se a requisição foi bem-sucedida
        String payload = http.getString(); // Obter resposta do servidor
        Serial.println("Resposta do servidor:" + payload);

        if (payload == "\ntrue") {
            // Cartão identificado com sucesso
            Serial.println("\nCartão Identificado");
            digitalWrite(LedVerde, HIGH);
            digitalWrite(tranca, HIGH);
            delay(5000); // Manter a tranca aberta por 5 segundos
            digitalWrite(tranca, LOW);
            digitalWrite(LedVerde, LOW);
            Serial.println("Tranca Fechada");
        } else {
            // Cartão não identificado
            Serial.println("\nCartão não identificado");
            digitalWrite(LedVermelho, HIGH);
            delay(2000); // Manter o LED vermelho aceso por 2 segundos
            digitalWrite(LedVermelho, LOW);
        }
    } else {
        Serial.print("Erro na requisição HTTP: ");
        Serial.println(httpResponseCode);
    }

    http.end(); // Encerrar conexão HTTP

    delay(2000); // Aguardar 2 segundos antes de verificar outro cartão RFID
}

String leituraDados() {
    mfrc522.PICC_DumpDetailsToSerial(&(mfrc522.uid));
    for (byte i = 0; i < 6; i++)
        key.keyByte[i] = 0xFF;

    String conteudo = "";

    for (byte i = 0; i < mfrc522.uid.size; i++) {
        Serial.print(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " ");
        Serial.print(mfrc522.uid.uidByte[i], HEX);
        conteudo.concat(String(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " "));
        conteudo.concat(String(mfrc522.uid.uidByte[i], HEX));
    }

    Serial.println();
    conteudo.toUpperCase();
    conteudo.trim();
    conteudo.remove(2, 1);
    conteudo.remove(4, 1);
    conteudo.remove(6, 1);
    return conteudo
