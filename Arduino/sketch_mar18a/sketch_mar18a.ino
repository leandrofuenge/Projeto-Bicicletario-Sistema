#include <WiFi.h>
#include <MFRC522.h>
#include <SPI.h>
#include <HTTPClient.h>

// Configurações da rede Wi-Fi
const char* SSID = "LEANDRO";
const char* PASSWORD = "32867393";

// Configurações do servidor HTTP
const char* HOST = "192.168.1.17";
const int PORT = 1010;
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

void connectToWiFi() {
    Serial.println("Conectando-se à rede Wi-Fi...");
    WiFi.begin(SSID, PASSWORD);
    int attempts = 0;
    while (WiFi.status() != WL_CONNECTED && attempts < 10) {
        delay(500);
        Serial.print(".");
        attempts++;
    }
    if (WiFi.status() != WL_CONNECTED) {
        Serial.println("\nFalha ao conectar-se à rede Wi-Fi.");
        return;
    }
    Serial.println("\nWi-Fi conectado.");
    Serial.println("Endereço IP: " + WiFi.localIP().toString());
}

bool httpGet(const char* endpoint, String params = "") {
    String url = "http://" + String(HOST) + ":" + String(PORT) + endpoint;
    if (params != "") {
        url += "?" + params;
    }

    HTTPClient http;
    http.begin(url);
    http.setTimeout(5000); // Definindo um tempo limite de 5 segundos
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

bool httpPost(const char* endpoint, String params = "") {
    String url = "http://" + String(HOST) + ":" + String(PORT) + endpoint;

    HTTPClient http;
    http.begin(url);
    http.addHeader("Content-Type", "application/x-www-form-urlencoded");
    http.setTimeout(5000); // Definindo um tempo limite de 5 segundos

    int httpResponseCode = http.POST(params);
    if (httpResponseCode == HTTP_CODE_OK) {
        Serial.println("Requisição HTTP POST bem-sucedida");
        return true;
    } else {
        Serial.print("Erro na requisição HTTP POST: ");
        Serial.println(httpResponseCode);
        return false;
    }
}

bool autenticarUsuario(String numeroDoCartao) {
    Serial.println("Autenticando usuário...");
    return httpGet(ENDPOINT_AUTENTICAR, "numeroDoCartao=" + numeroDoCartao);
}

bool verificarCreditos(String numeroDoCartao) {
    Serial.println("Verificando créditos do usuário...");
    return httpGet(ENDPOINT_VERIFICAR_CREDITOS, "numeroDoCartao=" + numeroDoCartao);
}

bool utilizarCredito(String numeroDoCartao) {
    Serial.println("Utilizando crédito...");
    
    // Construir os parâmetros da requisição POST
    String params = "numeroDoCartao=" + numeroDoCartao;

    // Fazer a requisição HTTP POST
    if (httpPost(ENDPOINT_UTILIZAR_CREDITO, params)) {
        return true;
    } else {
        return false;
    }
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



void loop() {
    if (!WiFi.isConnected()) {
        connectToWiFi();
        delay(1000); // Aguarda um segundo após tentar reconectar
        return;
    }

    if (!mfrc522.PICC_IsNewCardPresent())
        return;

    if (!mfrc522.PICC_ReadCardSerial())
        return;

    Serial.println("\nCartão detectado. Lendo dados...");
    String numeroDoCartao = leituraDados();

    if (autenticarUsuario(numeroDoCartao)) {
        Serial.println("Usuário autenticado com sucesso.");

        if (verificarCreditos(numeroDoCartao)) {
            // Se houver créditos suficientes, tenta utilizar um crédito
            if (utilizarCredito(numeroDoCartao)) {
                Serial.println("Usuário possui créditos suficientes.");
                digitalWrite(LED_VERDE, HIGH);
                digitalWrite(TRANCA, HIGH);
                delay(5000);
                digitalWrite(TRANCA, LOW);
                digitalWrite(LED_VERDE, LOW);
                Serial.println("Tranca fechada.");
            } else {
                // Se ocorrer um erro ao utilizar o crédito
                Serial.println("Erro ao utilizar crédito. Tranca não aberta.");
                digitalWrite(LED_VERMELHO, HIGH);
                delay(2000);
                digitalWrite(LED_VERMELHO, LOW);
            }
        } else {
            // Se não houver créditos suficientes
            Serial.println("Usuário possui créditos insuficientes.");
            digitalWrite(LED_VERMELHO, HIGH);
            delay(2000);
            digitalWrite(LED_VERMELHO, LOW);
            Serial.println("Tranca não aberta. Créditos insuficientes.");
        }
    } else {
        // Se o usuário não estiver autenticado
        Serial.println("Cartão não identificado.");
        digitalWrite(LED_VERMELHO, HIGH);
        delay(2000);
        digitalWrite(LED_VERMELHO, LOW);
    }

    delay(2000);
}
