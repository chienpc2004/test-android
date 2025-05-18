# 🔥 ESP32 + Firebase Realtime Database + DHT22 + Điều khiển LED

## 🛠️ Tính năng chính

- Kết nối WiFi và Firebase Realtime Database.
- Đọc dữ liệu từ cảm biến DHT22 (nhiệt độ và độ ẩm).
- Điều khiển LED từ xa thông qua Firebase (bật/tắt).
- Tự động nháy đèn cảnh báo khi nhiệt độ vượt quá 35°C.
- Gửi dữ liệu nhiệt độ và độ ẩm liên tục lên Firebase.

## 🧰 Phần cứng sử dụng

- ESP32
- Cảm biến DHT22
- 2 đèn LED
- Các điện trở (ví dụ: 220Ω)
- Dây nối và breadboard

## 🔧 Thư viện cần cài đặt

Hãy đảm bảo bạn đã cài các thư viện sau bằng Arduino Library Manager:

- `Firebase_ESP_Client`
- `DHT sensor library for ESPx`
- `WiFi.h` (đã có sẵn khi cài đặt ESP32 board)

## 📁 Cấu trúc Firebase Realtime Database

```json
{
  "esp32": {
    "light": "ON",
    "temperature": 28.5,
    "humidity": 60.2
  }
}
