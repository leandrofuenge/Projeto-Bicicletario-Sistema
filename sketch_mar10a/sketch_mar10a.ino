#include <WiFi.h>
#include <MFRC522.h>
#include <SPI.h>
#include <Wire.h>
#include <LiquidCrystal_I2C.h>
#include <WiFiClient.h>

const char* ssid = "LEANDRO";
const char* password = "32867393";
const char* host = "192.168.1.3";

String line = "";

#define SS_PIN 21
#define RST_PIN 22
#define LedVerde 26
#define LedVermelho 12
#define tranca 2
#define buzzer 15
#define SIZE_BUFFER  18
#define MAX_SIZE_BLOCK 16

MFRC522::MIFARE_Key key;
MFRC522 mfrc522(SS_PIN, RST_PIN);

LiquidCrystal_I2C lcd(0x27, 16, 2);

void setup() {
  Serial.begin(115200);
  SPI.begin(); 
  mfrc522.PCD_Init();
  lcd.init();
  lcd.backlight();
  lcd.setCursor(0, 0);
  lcd.print("Bem-vindo!");

  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("Wifi Connectado");
  Serial.println("IP:");
  Serial.println(WiFi.localIP());

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
  
  Serial.println("");
  Serial.print("Conectando ");
  Serial.println(host);
  WiFiClient client;
  const int httpPort = 80;
  if (!client.connect(host, httpPort)) {
    Serial.print("Falha na Conexão...");
    return;
  }

  String url = "/autenticar/validacao?numeroDoCartao=";
  url += leituraDados();

  client.print(String("GET ") + url + " HTTP/1.1\r\n" + "Host: " + host + "\r\n" + "Connection: close\r\n\r\n");
  unsigned long timeout = millis();
  while (client.available() == 0) {
    if (millis() - timeout > 5000) {
      Serial.println(">>> Cliente Timeout !");
      client.stop();
      return;
    }
  }

  while (client.available()) {
    line = client.readStringUntil('\r');
    Serial.print("Resposta do servidor:" + line);
  }

  if (line == "\ntrue") {
    Serial.println("\nCartão Identificado");
    delay(2000);
    digitalWrite(LedVerde, HIGH);
    lcd.clear();
    lcd.print("Acesso Liberado");
    digitalWrite(tranca, HIGH); 
    for(byte s = 5; s > 0; s--) {
      lcd.setCursor(8,1);
      lcd.print(s);
      Serial.println(s);
      delay(1000);
    }
    digitalWrite(tranca, LOW);
    digitalWrite(LedVerde, LOW);
    Serial.println("Tranca Fechada");
    lcd.clear();
  } else if (line == "\nfalse") {
    Serial.println("\nCartão não identificado");
    delay(2000);
  } else {
    Serial.println("\nErro na autenticação");
    delay(2000);
  }
}

String leituraDados() {
  mfrc522.PICC_DumpDetailsToSerial(&(mfrc522.uid));
  for (byte i = 0; i << 6; i++) 
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
  Serial.println(" ");
  conteudo.trim();
  conteudo.remove(2, 1);
  conteudo.remove(4, 1);
  conteudo.remove(6, 1);
  return conteudo;
}
