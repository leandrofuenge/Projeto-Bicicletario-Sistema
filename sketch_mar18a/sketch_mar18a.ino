#include <WiFi.h>
#include <MFRC522.h>
#include <SPI.h>
#include <HTTPClient.h>

// Configurações da rede Wi-Fi
const char* SSID = "LEANDRO";
const char* PASSWORD = "32867393";

// Configurações do servidor HTTP
const char* HOST = "192.168.1.3";
const int PORT = 8080;
const char* ENDPOINT_AUTENTICAR = "/usuarios/autenticar";
const char* ENDPOINT_VERIFICAR_CREDITOS = "/verificarcreditos";
const char* ENDPOINT_UTILIZAR_CREDITO = "/usuarios/utilizarcredito";

// Pinos do MFRC522
const int SS_PIN = 21;
const int RST_PIN = 22;

// Pinos dos LEDs, tranca e buzzer
const int LED_VERDE = 26;
const int LED_VERMELHO = 12;
const int TRANCA = 2;
const int BUZZER = 15;

MFRC522 mfrc522(SS_PIN, RST_PIN);

// Função para conectar à rede Wi-Fi
void connectToWiFi() {
    Serial.println("Conectando-se à rede Wi-Fi...");
    WiFi.begin(SSID, PASSWORD);
    while (WiFi.status() != WL_CONNECTED) {
        delay(500);
        Serial.print(".");
    }
    Serial.println("\nWi-Fi conectado.");
    Serial.println("Endereço IP: " + WiFi.localIP().toString());
}

// Função para fazer uma requisição GET HTTP
bool httpGet(const char* endpoint, String params = "") {
    String url = "http://" + String(HOST) + ":" + String(PORT) + endpoint;
    if (params != "") {
        url += "?" + params;
    }

    HTTPClient http;
    http.begin(url);
    int httpResponseCode = http.GET();
    if (httpResponseCode == HTTP_CODE_OK) {
        Serial.println("Requisição HTTP bem-sucedida");
        return true;
    } else {
        Serial.print("Erro na requisição HTTP: ");
        Serial.println(httpResponseCode);
        return false;
    }
}

// Função para autenticar o usuário
bool autenticarUsuario(String numeroDoCartao) {
    Serial.println("Autenticando usuário...");
    return httpGet(ENDPOINT_AUTENTICAR, "numeroDoCartao=" + numeroDoCartao);
}

// Função para verificar os créditos do usuário
bool verificarCreditos(String numeroDoCartao) {
    Serial.println("Verificando créditos do usuário...");
    return httpGet(ENDPOINT_VERIFICAR_CREDITOS, "numeroDoCartao=" + numeroDoCartao);
}

// Função para utilizar crédito do usuário
bool utilizarCredito(String numeroDoCartao) {
    Serial.println("Utilizando crédito...");
    return httpGet(ENDPOINT_UTILIZAR_CREDITO, "numeroDoCartao=" + numeroDoCartao);
}

// Função para realizar a leitura dos dados do cartão
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

// Configuração inicial
void setup() {
    Serial.begin(115200);
    SPI.begin();
    mfrc522.PCD_Init();

    pinMode(LED_VERDE, OUTPUT);
    pinMode(LED_VERMELHO, OUTPUT);
    pinMode(TRANCA, OUTPUT);
    pinMode(BUZZER, OUTPUT);

    connectToWiFi();
}

// Loop principal
void loop() {
    if (!mfrc522.PICC_IsNewCardPresent())
        return;

    if (!mfrc522.PICC_ReadCardSerial())
        return;

    Serial.println("\nCartão detectado. Lendo dados...");
    String numeroDoCartao = leituraDados();

    // Lógica para autenticar o usuário
    if (autenticarUsuario(numeroDoCartao)) {
        Serial.println("Usuário autenticado com sucesso.");

        // Verificar créditos antes de permitir o acesso
        if (verificarCreditos(numeroDoCartao)) {
            Serial.println("Usuário possui créditos suficientes.");
        } else {
            digitalWrite(LED_VERDE, HIGH);
            digitalWrite(TRANCA, HIGH);
            delay(5000); // Manter a tranca aberta por 5 segundos
            digitalWrite(TRANCA, LOW);
            digitalWrite(LED_VERDE, LOW);
            Serial.println("Tranca fechada.");
            // Utilizar crédito após acesso bem-sucedido
            utilizarCredito(numeroDoCartao);
        }
    } else {
        Serial.println("Cartão não identificado.");
        digitalWrite(LED_VERMELHO, HIGH);
        delay(2000); // Manter o LED vermelho aceso por 2 segundos
        digitalWrite(LED_VERMELHO, LOW);
    }

    delay(2000); // Aguardar 2 segundos antes de verificar outro cartão RFID
}
