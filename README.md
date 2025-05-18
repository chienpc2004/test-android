🔥 ESP32 + Firebase Realtime Database + DHT22 + LED Control
Dự án này sử dụng ESP32 để thu thập dữ liệu nhiệt độ và độ ẩm từ cảm biến DHT22, sau đó gửi dữ liệu lên Firebase Realtime Database. Ngoài ra, người dùng có thể điều khiển LED từ xa thông qua Firebase, đồng thời bật nháy một LED cảnh báo khi nhiệt độ vượt quá ngưỡng cho phép.

🛠️ Tính năng chính
📡 Kết nối WiFi và Firebase Realtime Database.

🌡️ Đọc dữ liệu từ cảm biến DHT22 (nhiệt độ, độ ẩm).

💡 Điều khiển LED từ Firebase (bật/tắt từ xa).

🚨 Tự động nháy đèn cảnh báo khi nhiệt độ vượt quá 35°C.

📤 Gửi dữ liệu nhiệt độ/độ ẩm liên tục lên Firebase.

🧰 Phần cứng sử dụng
ESP32

Cảm biến DHT22

2 đèn LED

Điện trở và dây nối

🔧 Thư viện cần cài đặt
Firebase_ESP_Client

DHT sensor library for ESPx

WiFi.h (mặc định có sẵn trong ESP32 core)

📁 Cấu trúc Firebase Realtime Database (ví dụ)
json
Copy
Edit
{
  "esp32": {
    "light": "ON",
    "temperature": 28.5,
    "humidity": 60.2
  }
}
💡 Cách sử dụng
Tạo dự án trên Firebase và bật Realtime Database.

Cập nhật FIREBASE_HOST, FIREBASE_AUTH, WiFi SSID và Password trong code.

Upload code lên ESP32.

Quan sát serial monitor để theo dõi dữ liệu.

Sử dụng Firebase để thay đổi giá trị esp32/light thành "ON" hoặc "OFF" để điều khiển LED.
