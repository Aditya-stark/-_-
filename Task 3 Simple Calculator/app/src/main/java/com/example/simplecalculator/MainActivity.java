package com.example.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private String operand1 = "";
    private String operator = "";
    private boolean isOperatorClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);
    }

    public void onNumberClick(View view) {
        if (isOperatorClicked) {
            resultTextView.setText("");
            isOperatorClicked = false;
        }

        String value = ((TextView) view).getText().toString();
        String oldValue = resultTextView.getText().toString();

        if (!oldValue.equals("0")) {
            resultTextView.setText(oldValue + value);
        } else {
            resultTextView.setText(value);
        }
    }

    public void onClearClick(View view) {
        resultTextView.setText("0");
        operand1 = "";
        operator = "";
        isOperatorClicked = false;
    }

    public void onOperatorClick(View view) {
        operand1 = resultTextView.getText().toString();
        operator = ((TextView) view).getText().toString();
        isOperatorClicked = true;
    }

    public void onEqualClick(View view) {
        if (!operand1.equals("") && !operator.equals("")) {
            String operand2 = resultTextView.getText().toString();
            double result = calculateResult(Double.parseDouble(operand1), Double.parseDouble(operand2), operator);
            resultTextView.setText(String.valueOf(result));
            operand1 = "";
            operator = "";
        }
    }

    private double calculateResult(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    return Double.NaN; // Indicate division by zero
                }
            default:
                return Double.NaN; // Invalid operator
        }
    }
}
