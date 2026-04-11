package com.example.madfiles;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RatingBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratingbar);

        ImageView ivCapturedImage = findViewById(R.id.ivCapturedImage);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        TextView tvRatingValue = findViewById(R.id.tvRatingValue);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        // Get the bitmap from intent
        Bitmap bitmap = getIntent().getParcelableExtra("capturedImage");
        if (bitmap != null) {
            ivCapturedImage.setImageBitmap(bitmap);
        }

        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
            tvRatingValue.setText("Rating: " + rating + " / 5.0");
        });

        btnSubmit.setOnClickListener(v -> {
            float rating = ratingBar.getRating();
            Toast.makeText(RatingBarActivity.this, "Submitted Rating: " + rating, Toast.LENGTH_SHORT).show();
            // You can add logic here to save the rating or send it to a server
        });
    }
}