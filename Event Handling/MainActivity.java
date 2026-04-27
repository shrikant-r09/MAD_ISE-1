package com.example.eventhandling;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView statusText;
    private TextView detailsText;
    private Button eventButton;
    private EditText eventEditText;

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

        statusText = findViewById(R.id.statusText);
        detailsText = findViewById(R.id.detailsText);
        eventButton = findViewById(R.id.eventButton);
        eventEditText = findViewById(R.id.eventEditText);

        // 1. Click Listener
        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStatus("Button Clicked", "Action: setOnClickListener");
            }
        });

        // 2. Long Click Listener
        eventButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                updateStatus("Button Long Clicked", "Action: setOnLongClickListener");
                return true; // Return true to indicate the event is consumed
            }
        });

        // 3. Focus Change Listener
        eventEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String focusStatus = hasFocus ? "Focused" : "Lost Focus";
                updateStatus("EditText " + focusStatus, "Action: setOnFocusChangeListener");
            }
        });

        // 4. Text Watcher (Event on typing)
        eventEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateStatus("Text Changing...", "Character added/removed: " + s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void updateStatus(String status, String details) {
        statusText.setText(status);
        detailsText.setText(details);
    }
}
