//inclusão de algumas bibliotecas
#include <WiFi.h>
#include <MFRC522.h>
#include <SPI.h>
#include <Wire.h>
#include <LiquidCrystal_I2C.h>

const char* ssid = "LEANDRO";
const char* password = "32867393";
const char* host = "192.168.1.3";

String line = "";

//define alguns pinos do esp32 que serao conectados aos modulos e componentes
#define SS_PIN 21
#define RST_PIN 22
#define LedVerde 26
#define LedVermelho 12
#define tranca 2
#define buzzer 15
#define SIZE_BUFFER  18
#define MAX_SIZE_BLOCK 16

//Esse objeto 'chave' é utilizado para autenticação
MFRC522::MIFARE_Key key;

//Código de status de retorno da autenticação
MFRC522::StatusCode status;

//Definições pino modulo MFRC522
MFRC522 mfrc522(SS_PIN, RST_PIN);

LiquidCrystal_I2C lcd(0x27, 16, 2); // define informacoes do lcd como o endereço I2C (0x27) e tamanho do mesmo

void setup() {
  //Inicia a serial
  Serial.begin(115200);
  // Inicia o SPI bus
  SPI.begin(); 

  // Inicia MFRC522
  mfrc522.PCD_Init();
  Serial.println("Conectando com ");
  Serial.println(ssid);

  WiFi.begin(ssid, password);


while (WiFi.status() != WL_CONNECTED) {
  delay(500);
  Serial.print(".");
}
Serial.println("Wifi Connectado");
Serial.println("IP:");
Serial.println(WiFi.localIP());

 // define alguns pinos como saida
  pinMode(LedVerde, OUTPUT);
  pinMode(LedVermelho, OUTPUT);
  pinMode(tranca, OUTPUT);
  pinMode(buzzer, OUTPUT);


}

void loop()
{
   if ( ! mfrc522.PICC_IsNewCardPresent())  //Aguarda a aproximação do cartão
     return;                
  
  if ( ! mfrc522.PICC_ReadCardSerial()) //Seleciona um dos cartões
    return;                  
  
  Serial.println("");
  Serial.println("Conectando ");
  Serial.println(host);
  WiFiClient client;
  const int httpPort = 8080;
  if(!client.connect(host, httpPort)) {
    Serial.println("Falha na Conexão...");
    return;
  }


  String url = "/autenticar/validacao?numeroDoCartao=";

  url += leituraDados();

  Serial.println(url);

  Serial.println("Requisitando URL:");
  Serial.println(url);


  client.print(String("GET ") + url + " HTTP/1.1\r\n" + "Host: " + host + "\r\n" + "Connection:  close\r\n\r\n");
  unsigned long timeout = millis();
  while(client.available() == 0) {
    if (millis() - timeout > 5000) {
      Serial.println(">>> Cliente Timeout !");
      client.stop();
      return;
    }
  }

  while(client.available()) {
    line = client.readStringUntil('\r');
    Serial.println("Resposta do servidor:" + line);
  }

  if(line == "\ntrue")
  {
   
    Serial.println("\nCartão Identificado");
     delay(2000);

     digitalWrite(LedVerde, HIGH);            // ligamos o led verde
      lcd.clear();                             // limpamos oque havia sido escrito no lcd
      Serial.print("Acesso Liberado");            // informamos pelo lcd que a tranca foi aberta
 
      digitalWrite(tranca, HIGH);              //abrimos a tranca por 5 segundos

for(byte s = 5; s > 0; s--){             //vai informando ao usuario quantos segundos faltao para a tranca ser fechada
        lcd.setCursor(8,1);
        lcd.print(s);
        Serial.println(s);
        delay(1000);
      }

       digitalWrite(tranca, LOW);               // fecha a tranca
      digitalWrite(LedVerde, LOW);             // e desliga o led
      Serial.println("Tranca Fechada");
      lcd.clear();                             // limpa os caracteres q estao escritos no lcd

 

  }
  else if (line == "\nfalse")
  {
 
    Serial.println("\nCartão não identificado");
       delay(2000);
  }
  else 
     Serial.print("\nTESTE");
     Serial.print("\nESPERA");
     Serial.print("\nConexão fechada");
     delay(2000);
  }


 //faz a leitura dos dados do cartão/tag
  String leituraDados(){
  mfrc522.PICC_DumpDetailsToSerial(&(mfrc522.uid)); //imprime os detalhes do cartão/tag
  for (byte i = 0; i<< 6; i++) //Prepara a chave - todas as chaves estão configuradas para FFFFFFFFFFh (Padrão de fábrica).
  key.keyByte[i] = 0xFF;

  byte buffer[SIZE_BUFFER] = {0}; //Buffer para colocar os dados lidos
  byte  bloco = 1;//bloco que faremos a operação
  byte tamanho = SIZE_BUFFER;
  String conteudo = "";
  byte letra;

  for (byte i = 0; i < mfrc522.uid.size; i++)
  {
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
