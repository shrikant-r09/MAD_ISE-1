package com.example.madfiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupButton(R.id.btn_bgclr, BgClrChangeActivity.class);
        setupButton(R.id.btn_calculator, CalculatorActivity.class);
        setupButton(R.id.btn_constraint, ConstraintLayoutActivity.class);
        setupButton(R.id.btn_explicit_resume, ExplicitResumeActivity.class);
        setupButton(R.id.btn_frame, FrameLayoutActivity.class);
        setupButton(R.id.btn_grid, GridLayoutActivity.class);
        setupButton(R.id.btn_rating, RatingActivity.class);
        setupButton(R.id.btn_implicit, ImplicitActivity.class);
        setupButton(R.id.btn_linear, LinearLayoutActivity.class);
        setupButton(R.id.btn_relative, RelativeLayoutActivity.class);
        setupButton(R.id.btn_resume, ResumeActivity.class);
        setupButton(R.id.btn_table, TableLayoutActivity.class);
    }

    private void setupButton(int id, final Class<?> activityClass) {
        MaterialButton button = findViewById(id);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, activityClass));
                }
            });
        }
    }
}
