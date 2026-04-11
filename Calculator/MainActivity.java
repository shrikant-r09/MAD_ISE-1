package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editNum1, editNum2;
    TextView txtResult;
    Button btnAdd, btnSub, btnMul, btnDiv, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        editNum1 = findViewById(R.id.num1);
        editNum2 = findViewById(R.id.num2);
        txtResult = findViewById(R.id.txtResult);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        btnReset = findViewById(R.id.btnReset);

        // Set Click Listeners
        btnAdd.setOnClickListener(v -> calculate('+'));
        btnSub.setOnClickListener(v -> calculate('-'));
        btnMul.setOnClickListener(v -> calculate('*'));
        btnDiv.setOnClickListener(v -> calculate('/'));

        // Reset Logic
        btnReset.setOnClickListener(v -> {
            editNum1.setText("");
            editNum2.setText("");
            txtResult.setText("Result: 0");
        });
    }

    private void calculate(char operator) {
        String s1 = editNum1.getText().toString();
        String s2 = editNum2.getText().toString();

        if (s1.isEmpty() || s2.isEmpty()) {
            txtResult.setText("Enter both numbers!");
            return;
        }

        double n1 = Double.parseDouble(s1);
        double n2 = Double.parseDouble(s2);
        double result = 0;

        switch (operator) {
            case '+': result = n1 + n2; break;
            case '-': result = n1 - n2; break;
            case '*': result = n1 * n2; break;
            case '/':
                if (n2 != 0) result = n1 / n2;
                else {
                    txtResult.setText("Cannot divide by 0");
                    return;
                }
                break;
        }
        txtResult.setText("Result: " + result);
    }
}