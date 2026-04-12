package com.example.rating;

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
        Button btnSubmitRating = findViewById(R.id.btnSubmitRating);

        Bitmap bitmap = getIntent().getParcelableExtra("capturedImage");
        if (bitmap != null) {
            ivCapturedImage.setImageBitmap(bitmap);
        }

        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
            tvRatingValue.setText("Rating: " + rating + " / 5.0");
        });

        btnSubmitRating.setOnClickListener(v -> {
            float rating = ratingBar.getRating();
            Toast.makeText(RatingBarActivity.this, "Rating submitted: " + rating, Toast.LENGTH_SHORT).show();
            finish(); // Optional: close activity after submission
        });
    }
}