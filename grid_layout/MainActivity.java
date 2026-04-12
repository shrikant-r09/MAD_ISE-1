package com.example.gridlayout;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        Button btnCalculator = findViewById(R.id.btn_calculator);
        Button btnCalendar = findViewById(R.id.btn_calendar);
        Button btnClock = findViewById(R.id.btn_clock);
        Button btnOther = findViewById(R.id.btn_other);

        btnCalculator.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_APP_CALCULATOR);
            try {
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "Calculator app not found", Toast.LENGTH_SHORT).show();
            }
        });

        btnCalendar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(CalendarContract.CONTENT_URI);
            startActivity(intent);
        });

        btnClock.setOnClickListener(v -> {
            Intent intent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
            startActivity(intent);
        });

        btnOther.setOnClickListener(v -> {
            Toast.makeText(this, "Other task triggered!", Toast.LENGTH_SHORT).show();
        });
    }
}
