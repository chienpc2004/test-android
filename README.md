# ESP32 + DHT22 + LEDs with Firebase and App 

This project demonstrates how to use an **ESP32** microcontroller to:

- Read **temperature and humidity** from a **DHT22** sensor
- **Send** sensor data to **Firebase**
- **Receive** control signals from Firebase to **LEDs**
- **Control LEDs using a custom Android mobile app**

---

## 🔧 Hardware Requirements

- ESP32 development board
- DHT22 sensor
- LEDs + 220Ω resistors
- Jumper wires, Breadboard
- Internet-connected Wi-Fi network

---

## 📷 Wiring Diagram

| Component | ESP32 GPIO |
|----------|------------|
| DHT22 (Data) | GPIO 4 |
| LED 1        | GPIO 25 |


---

## 📲 Firebase Setup

1. Create a project at [Firebase Console](https://console.firebase.google.com/)
2. Enable **Email/Password** authentication
3. Go to **Realtime Database** → Create database in test mode
4. Copy your **API Key** and **Database URL** from **Project Settings**
5. Update the following values in `main.ino`:
   Go to Library Manager and install Mobitz's Firebase package
   ```cpp
   #include <WiFi.h>
   #include <Firebase_ESP_Client.h>
   #include <DHT.h>
   const char* ssid = "Name Wifi";
   const char* password = "Password Wifi";
   #define FIREBASE_HOST "https//"
   #define FIREBASE_AUTH "secrets code"

---

🛠️ Step-by-Step Android Studio Setup
1. **Create Android Project**
  -Open Android Studio
   
  - Click New Project → Choose Empty Activity
  
  - Name your app (e.g., ESP32ControlApp)
  
  - Language: Java or Kotlin (examples below use Java)
  
  - Finish setup

2. **Connect Firebase to Your Project**
  - In Android Studio:

  - Open Tools > Firebase

  - Under Realtime Database, click:

  - "Set up Firebase Realtime Database"

  - Connect to your Firebase project

  - Accept changes to build.gradle

3. **Add Dependencies (Optional Manual)**
   
  - If Firebase Assistant is not available, manually add these to **app/build.gradle**:

  - Add to the bottom of **build.gradle** (Project):

  - implementation 'com.google.firebase:firebase-database:20.3.0'

  - implementation 'com.google.firebase:firebase-auth:22.3.1'

---

   In **app/build.gradle**, apply:

  - classpath 'com.google.gms:google-services:4.4.1'
  - apply plugin: 'com.google.gms.google-services'

---

# Build Layout and Run Program
