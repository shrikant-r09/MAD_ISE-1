package com.example.madfiles;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class BgClrChangeActivity extends AppCompatActivity {

    Button btnRed, btnGreen, btnBlue;
    LinearLayout layoutBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Error 1: Wrong layout file was being set (was activity_main)
        setContentView(R.layout.activity_bg_clr_change);

        // Error 2: IDs did not match activity_bg_clr_change.xml
        btnRed = findViewById(R.id.btn_red);
        btnGreen = findViewById(R.id.btn_green);
        btnBlue = findViewById(R.id.btn_blue);
        layoutBg = findViewById(R.id.layout_bg);

        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutBg.setBackgroundColor(Color.RED);
            }
        });

        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutBg.setBackgroundColor(Color.GREEN);
            }
        });

        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutBg.setBackgroundColor(Color.BLUE);
            }
        });
    }
}
