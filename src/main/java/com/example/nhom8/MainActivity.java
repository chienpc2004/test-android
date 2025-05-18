package com.example.nhom8;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.appcompat.widget.SwitchCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Lấy tham chiếu đến TextView
        TextView temperatureText = findViewById(R.id.temperatureText);
        TextView humidityText = findViewById(R.id.humidityText);

        // Kết nối Firebase
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("esp32");
        databaseRef.setValue("Connected")
                .addOnSuccessListener(aVoid -> {
                    Log.d("FirebaseTest", "Kết nối Firebase thành công.");
                })
                .addOnFailureListener(e -> {
                    Log.e("FirebaseTest", "Lỗi kết nối Firebase: " + e.getMessage());
                });


        // Đọc dữ liệu từ Firebase
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Double temp = snapshot.child("temperature").getValue(Double.class);
                    Double humi = snapshot.child("humidity").getValue(Double.class);

                    temperatureText.setText( temp + " °C");
                    humidityText.setText("Độ ẩm: " + humi + " %");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                temperatureText.setText("Lỗi!");
                humidityText.setText(error.getMessage());
            }
        });

// ánh xạ switch
        SwitchCompat lightToggleSwitch = findViewById(R.id.lightToggleSwitch);

// Firebase reference
        DatabaseReference lightRef = databaseRef.child("light");

// Cập nhật trạng thái từ Firebase
        lightRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String lightState = snapshot.getValue(String.class);
                if ("ON".equals(lightState)) {
                    lightToggleSwitch.setChecked(true);
                    lightToggleSwitch.setText("Tắt đèn");
                } else {
                    lightToggleSwitch.setChecked(false);
                    lightToggleSwitch.setText("Bật đèn");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("Firebase", "Lỗi đọc light: " + error.getMessage());
            }
        });
// Sự kiện khi người dùng bật/tắt switch
        lightToggleSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String newState = isChecked ? "ON" : "OFF";
            lightRef.setValue(newState)
                    .addOnSuccessListener(aVoid -> Log.d("Firebase", "Đã cập nhật đèn: " + newState))
                    .addOnFailureListener(e -> Log.e("Firebase", "Lỗi cập nhật đèn: " + e.getMessage()));
        });
    }
}