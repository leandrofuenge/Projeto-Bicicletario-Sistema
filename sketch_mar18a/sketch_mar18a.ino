#include <WiFi.h>
#include <MFRC522.h>
#include <SPI.h>
#include <HTTPClient.h>

// Definições de pinos e outras variáveis globais
const char* ssid = "LEANDRO";
const char* password = "32867393";
const char* host = "192.168.1.3";
const int port = 8080;
const char* javaEndpoint = "/usuarios/autenticar";

const int SS_PIN = 21;
const int RST_PIN = 22;
const int LedVerde = 26;
const int LedVermelho = 12;
const int Tranca = 2;
const int Buzzer = 15;

MFRC522::MIFARE_Key key;
MFRC522 mfrc522(SS_PIN, RST_PIN);

// Função para conectar-se à rede Wi-Fi
void connectToWiFi() {
    Serial.println("Conectando-se à rede Wi-Fi...");
    WiFi.begin(ssid, password);
    while (WiFi.status() != WL_CONNECTED) {
        delay(500);
        Serial.print(".");
    }
    Serial.println("\nWi-Fi conectado.");
    Serial.println("Endereço IP: " + WiFi.localIP().toString());
}

// Função para autenticar o usuário no servidor remoto
bool autenticarUsuario(const String& numeroDoCartao, int valorDoPlano) {
    Serial.println("Autenticando usuário...");
    String url = "http://" + String(host) + ":" + String(port) + javaEndpoint;
    url += "?numeroDoCartao=" + numeroDoCartao + "&valorDoPlano=" + String(valorDoPlano);

    HTTPClient http;
    http.begin(url);
    int httpResponseCode = http.GET();

    if (httpResponseCode == HTTP_CODE_OK) {
        String payload = http.getString();
        Serial.println("Resposta do servidor: " + payload);
        return payload == "true";
    } else {
        Serial.print("Erro na requisição HTTP: ");
        Serial.println(httpResponseCode);
        return false;
    }
}

void setup() {
    Serial.begin(115200);
    SPI.begin();
    mfrc522.PCD_Init();

    pinMode(LedVerde, OUTPUT);
    pinMode(LedVermelho, OUTPUT);
    pinMode(Tranca, OUTPUT);
    pinMode(Buzzer, OUTPUT);

    connectToWiFi();
}

void loop() {
    if (!mfrc522.PICC_IsNewCardPresent())
        return;

    if (!mfrc522.PICC_ReadCardSerial())
        return;

    Serial.println("\nCartão detectado. Lendo dados...");
    String numeroDoCartao = leituraDados();
    int valorDoPlano = 200; // Valor do plano fixo em 200

    if (autenticarUsuario(numeroDoCartao, valorDoPlano)) {
        // Cartão identificado com sucesso
        Serial.println("Usuário autenticado com sucesso.");
        digitalWrite(LedVerde, HIGH);
        digitalWrite(Tranca, HIGH);
        delay(5000); // Manter a tranca aberta por 5 segundos
        digitalWrite(Tranca, LOW);
        digitalWrite(LedVerde, LOW);
        Serial.println("Tranca fechada.");
    } else {
        // Cartão não identificado
        Serial.println("Usuário não autenticado.");
        digitalWrite(LedVermelho, HIGH);
        delay(2000); // Manter o LED vermelho aceso por 2 segundos
        digitalWrite(LedVermelho, LOW);
    }

    delay(2000); // Aguardar 2 segundos antes de verificar outro cartão RFID
}

String leituraDados() {
    String conteudo = "";

    mfrc522.PICC_DumpToSerial(&(mfrc522.uid));
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
    return conteudo;
}
