package com.example.registrationform;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etEmail, etNumber, etAddress, etWork;
    private RadioGroup rgGender;
    private Button btnSubmit, btnLoad;
    private TextView tvDisplay;
    private static final String FILE_NAME = "mydata.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etNumber = findViewById(R.id.etNumber);
        etAddress = findViewById(R.id.etAddress);
        etWork = findViewById(R.id.etWork);
        rgGender = findViewById(R.id.rgGender);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnLoad = findViewById(R.id.btnLoad);
        tvDisplay = findViewById(R.id.tvDisplay);

        // Submit Button Logic
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String number = etNumber.getText().toString().trim();
                String address = etAddress.getText().toString().trim();
                String work = etWork.getText().toString().trim();
                int selectedId = rgGender.getCheckedRadioButtonId();

                if (name.isEmpty() || email.isEmpty() || number.isEmpty() || 
                    address.isEmpty() || work.isEmpty() || selectedId == -1) {
                    Toast.makeText(MainActivity.this, "Please fill all details", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton rbSelected = findViewById(selectedId);
                String gender = rbSelected.getText().toString();

                // Create a record string (using | as delimiter because address might contain commas)
                String record = name + "|" + email + "|" + number + "|" + address + "|" + work + "|" + gender + "\n";
                
                // Save data to file
                saveToFile(record);

                // Clear fields
                etName.setText("");
                etEmail.setText("");
                etNumber.setText("");
                etAddress.setText("");
                etWork.setText("");
                rgGender.clearCheck();
            }
        });

        // Load Data Button Logic
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rawData = readFromFile();
                if (rawData.isEmpty()) {
                    tvDisplay.setText("No data available in file.");
                    return;
                }

                // Format the raw data into a tabular string
                StringBuilder builder = new StringBuilder();
                // Table Header
                builder.append(String.format("%-10s | %-15s | %-10s | %-15s | %-10s | %-6s\n", 
                                            "Name", "Email", "Phone", "Address", "Work", "Sex"));
                builder.append("------------------------------------------------------------------------------------------\n");

                String[] lines = rawData.split("\n");
                for (String line : lines) {
                    String[] parts = line.split("\\|");
                    if (parts.length == 6) {
                        builder.append(String.format("%-10s | %-15s | %-10s | %-15s | %-10s | %-6s\n", 
                                                    parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                    }
                }

                tvDisplay.setText(builder.toString());
            }
        });
    }

    private void saveToFile(String data) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_APPEND);
            fos.write(data.getBytes());
            Toast.makeText(this, "Data Submitted and Saved!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving data", Toast.LENGTH_SHORT).show();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String readFromFile() {
        FileInputStream fis = null;
        StringBuilder sb = new StringBuilder();
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
