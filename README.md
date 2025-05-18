# 🔥 ESP32 + Firebase Realtime Database + DHT22 + LED Control

This project uses an **ESP32** to collect **temperature** and **humidity** data from a **DHT22** sensor and send it to **Firebase Realtime Database**. It also allows **remote LED control** via Firebase and includes a **temperature alert** using a blinking LED when the temperature exceeds a threshold.

## 🛠️ Features

- 📡 Connects to WiFi and Firebase Realtime Database
- 🌡️ Reads temperature and humidity from DHT22
- 💡 Controls an LED remotely via Firebase
- 🚨 Blinks a second LED when temperature > 35°C
- 📤 Uploads data to Firebase every 2 seconds

## 🧰 Hardware Requirements

- ESP32 board
- DHT22 temperature & humidity sensor
- 2 LEDs
- 2 Resistors (220Ω recommended)
- Breadboard and jumper wires

## 🔧 Required Libraries

Make sure to install the following libraries via the Arduino Library Manager:

- [`Firebase_ESP_Client`](https://github.com/mobizt/Firebase-ESP-Client)
- [`DHT sensor library for ESPx`](https://github.com/beegee-tokyo/DHTesp)

## 📁 Firebase Realtime Database Structure

```json
{
  "esp32": {
    "light": "ON",
    "temperature": 28.5,
    "humidity": 60.2
  }
}
