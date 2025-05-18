#include <WiFi.h>
#include <Firebase_ESP_Client.h>
#include <DHT.h>

// ==== THÔNG TIN WIFI VÀ FIREBASE ====
const char* ssid = "VChien";
const char* password = "10112004";

#define FIREBASE_HOST "https://test-7f6b9-default-rtdb.firebaseio.com/"
#define FIREBASE_AUTH "ruQHNRBGdYRIsb8Vo75hhsKjbqbND9pxmkKn7VES"
#define API_KEY ""  // Nếu chỉ dùng RTDB thì có thể để trống

// ==== CẤU HÌNH CẢM BIẾN DHT11 ====
#define DHTPIN 4
#define DHTTYPE DHT22
DHT dht(DHTPIN, DHTTYPE);

// ==== CẤU HÌNH LED ====
#define LED_PIN 5  // Chỉ dùng 1 LED tại GPIO5
#define LED_PIN_2 19 // LED nháy khi nhiệt độ > 35

// ==== CẤU HÌNH FIREBASE ====
FirebaseData fbdo;
FirebaseAuth auth;
FirebaseConfig config;

void setup() {
  Serial.begin(115200);
  dht.begin();

  // Kết nối WiFi
  WiFi.begin(ssid, password);
  Serial.print("Đang kết nối WiFi");
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("\n Đã kết nối WiFi");

  // Cấu hình Firebase
  config.host = FIREBASE_HOST;
  config.signer.tokens.legacy_token = FIREBASE_AUTH;

  Firebase.begin(&config, &auth);
  Firebase.reconnectWiFi(true);

  // Cấu hình chân LED
  pinMode(LED_PIN, OUTPUT);
  digitalWrite(LED_PIN, LOW);
//nhay den
  pinMode(LED_PIN_2, OUTPUT);
}

void loop() {
  // ==== Đọc trạng thái LED từ Firebase ====
  if (Firebase.RTDB.getString(&fbdo, "/esp32/light")) {
    String lightState = fbdo.stringData();
    digitalWrite(LED_PIN, (lightState == "ON") ? HIGH : LOW);
    Serial.printf("Trạng thái đèn: %s\n", lightState.c_str());
  } else {
    Serial.printf("Lỗi đọc trạng thái đèn: %s\n", fbdo.errorReason().c_str());
  }

  // ==== Đọc nhiệt độ và độ ẩm từ DHT22 ====
  float temperature = dht.readTemperature();
  float humidity = dht.readHumidity();

//nhay den
   if (temperature > 35) {
    // Nháy đèn khi nhiệt độ > 35
    digitalWrite(LED_PIN_2, HIGH);  // Bật đèn
    delay(500);
    digitalWrite(LED_PIN_2, LOW);   // Tắt đèn
    delay(500);
  } else {
    // Tắt đèn khi nhiệt độ <= 35
    digitalWrite(LED_PIN_2, LOW);
  }

  if (!isnan(temperature) && !isnan(humidity)) {
    Serial.printf(" Temp: %.2f°C |  Humidity: %.2f%%\n", temperature, humidity);

    // Gửi dữ liệu lên Firebase
    Firebase.RTDB.setFloat(&fbdo, "/esp32/temperature", temperature);
    Firebase.RTDB.setFloat(&fbdo, "/esp32/humidity", humidity);

    
  } else {
    Serial.println(" Lỗi đọc DHT22");
  }

  delay(2000);
}