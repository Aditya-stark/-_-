package com.example.unitconvertor;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText valueInput;
    Spinner inputTypeSpinner, outputTypeSpinner;
    Button convertButton;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valueInput = findViewById(R.id.valueInput);
        inputTypeSpinner = findViewById(R.id.inputTypeSpinner);
        outputTypeSpinner = findViewById(R.id.outputTypeSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultText = findViewById(R.id.resultText);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.length_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputTypeSpinner.setAdapter(adapter);
        outputTypeSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performConversion();
            }
        });
    }

    private void performConversion() {
        String valueStr = valueInput.getText().toString();
        if (valueStr.isEmpty()) {
            resultText.setText("Please enter a value.");
            return;
        }

        double value = Double.parseDouble(valueStr);
        String inputUnit = inputTypeSpinner.getSelectedItem().toString();
        String outputUnit = outputTypeSpinner.getSelectedItem().toString();

        double result = convertUnits(value, inputUnit, outputUnit);
        resultText.setText(String.format("%.2f %s = %.2f %s", value, inputUnit, result, outputUnit));
    }

    private double convertUnits(double value, String inputUnit, String outputUnit) {
        // Conversion logic based on input and output units
        double result = 0.0;

        switch (inputUnit) {
            case "Kilometers":
                result = convertFromKilometers(value, outputUnit);
                break;
            case "Meters":
                result = convertFromMeters(value, outputUnit);
                break;
            case "Centimeters":
                result = convertFromCentimeters(value, outputUnit);
                break;
            case "Millimeters":
                result = convertFromMillimeters(value, outputUnit);
                break;
            case "Micrometers":
                result = convertFromMicrometers(value, outputUnit);
                break;
            default:
                resultText.setText("Invalid input unit.");
        }

        return result;
    }

    private double convertFromKilometers(double value, String outputUnit) {
        switch (outputUnit) {
            case "Meters":
                return value * 1000.0;
            case "Centimeters":
                return value * 100000.0;
            case "Millimeters":
                return value * 1_000_000.0;
            case "Micrometers":
                return value * 1_000_000_000.0;
            default:
                return value;
        }
    }
    private double convertFromMeters(double value, String outputUnit) {
        switch (outputUnit) {
            case "Kilometers":
                return value / 1000.0;
            case "Centimeters":
                return value * 100.0;
            case "Millimeters":
                return value * 1000.0;
            case "Micrometers":
                return value * 1_000_000.0;
            default:
                return value;
        }
    }

    private double convertFromCentimeters(double value, String outputUnit) {
        switch (outputUnit) {
            case "Kilometers":
                return value / 100000.0;
            case "Meters":
                return value / 100.0;
            case "Millimeters":
                return value * 10.0;
            case "Micrometers":
                return value * 10_000.0;
            default:
                return value;
        }
    }

    private double convertFromMillimeters(double value, String outputUnit) {
        switch (outputUnit) {
            case "Kilometers":
                return value / 1_000_000.0;
            case "Meters":
                return value / 1000.0;
            case "Centimeters":
                return value / 10.0;
            case "Micrometers":
                return value * 1000.0;
            default:
                return value;
        }
    }

    private double convertFromMicrometers(double value, String outputUnit) {
        switch (outputUnit) {
            case "Kilometers":
                return value / 1_000_000_000.0;
            case "Meters":
                return value / 1_000_000.0;
            case "Centimeters":
                return value / 10_000.0;
            case "Millimeters":
                return value / 1000.0;
            default:
                return value;
        }
    }
}