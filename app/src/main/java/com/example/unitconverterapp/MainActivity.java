package com.example.unitconverterapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerLengthFrom, spinnerLengthTo, spinnerWeightFrom, spinnerWeightTo, spinnerTempFrom, spinnerTempTo;
    private EditText editTextLength, editTextWeight, editTextTemp;
    private Button buttonConvertLength, buttonConvertWeight, buttonConvertTemp;
    private TextView textViewLengthResult, textViewWeightResult, textViewTempResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI Elements
        spinnerLengthFrom = findViewById(R.id.spinner_length_from);
        spinnerLengthTo = findViewById(R.id.spinner_length_to);
        spinnerWeightFrom = findViewById(R.id.spinner_weight_from);
        spinnerWeightTo = findViewById(R.id.spinner_weight_to);
        spinnerTempFrom = findViewById(R.id.spinner_temp_from);
        spinnerTempTo = findViewById(R.id.spinner_temp_to);

        editTextLength = findViewById(R.id.editText_length);
        editTextWeight = findViewById(R.id.editText_weight);
        editTextTemp = findViewById(R.id.editText_temp);

        buttonConvertLength = findViewById(R.id.button_convert_length);
        buttonConvertWeight = findViewById(R.id.button_convert_weight);
        buttonConvertTemp = findViewById(R.id.button_convert_temp);

        textViewLengthResult = findViewById(R.id.textView_length_result);
        textViewWeightResult = findViewById(R.id.textView_weight_result);
        textViewTempResult = findViewById(R.id.textView_temp_result);

        // Populate Spinners
        setupSpinners();

        // Handle Button Clicks
        buttonConvertLength.setOnClickListener(view -> convertLength());
        buttonConvertWeight.setOnClickListener(view -> convertWeight());
        buttonConvertTemp.setOnClickListener(view -> convertTemperature());
    }

    private void setupSpinners() {
        // Length Conversion Options
        String[] lengthUnits = {"Inch", "Foot", "Yard", "Mile"};
        String[] lengthConvertTo = {"Centimeter", "Meter", "Kilometer"};

        // Weight Conversion Options
        String[] weightUnits = {"Pound", "Ounce", "Ton"};
        String[] weightConvertTo = {"Kilogram", "Gram"};

        // Temperature Conversion Options
        String[] tempUnits = {"Celsius", "Fahrenheit", "Kelvin"};

        // Setting Adapters for Spinners
        setSpinnerAdapter(spinnerLengthFrom, lengthUnits);
        setSpinnerAdapter(spinnerLengthTo, lengthConvertTo);
        setSpinnerAdapter(spinnerWeightFrom, weightUnits);
        setSpinnerAdapter(spinnerWeightTo, weightConvertTo);
        setSpinnerAdapter(spinnerTempFrom, tempUnits);
        setSpinnerAdapter(spinnerTempTo, tempUnits);
    }

    private void setSpinnerAdapter(Spinner spinner, String[] items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void convertLength() {
        String fromUnit = spinnerLengthFrom.getSelectedItem().toString();
        String toUnit = spinnerLengthTo.getSelectedItem().toString();
        String input = editTextLength.getText().toString();

        if (input.isEmpty()) {
            Toast.makeText(this, "Please enter a value!", Toast.LENGTH_SHORT).show();
            return;
        }

        double value = Double.parseDouble(input);
        double result = 0;

        switch (fromUnit) {
            case "Inch":
                result = value * 2.54;
                break;
            case "Foot":
                result = value * 30.48;
                break;
            case "Yard":
                result = value * 91.44;
                break;
            case "Mile":
                result = value * 1.60934;
                break;
        }

        if (toUnit.equals("Meter")) {
            result /= 100;
        } else if (toUnit.equals("Kilometer")) {
            result /= 100000;
        }

        textViewLengthResult.setText("Result: " + result + " " + toUnit);
    }

    private void convertWeight() {
        String fromUnit = spinnerWeightFrom.getSelectedItem().toString();
        String toUnit = spinnerWeightTo.getSelectedItem().toString();
        String input = editTextWeight.getText().toString();

        if (input.isEmpty()) {
            Toast.makeText(this, "Please enter a value!", Toast.LENGTH_SHORT).show();
            return;
        }

        double value = Double.parseDouble(input);
        double result = 0;

        switch (fromUnit) {
            case "Pound":
                result = value * 0.453592;
                break;
            case "Ounce":
                result = value * 28.3495;
                break;
            case "Ton":
                result = value * 907.185;
                break;
        }

        if (toUnit.equals("Gram")) {
            result *= 1000;
        }

        textViewWeightResult.setText("Result: " + result + " " + toUnit);
    }

    private void convertTemperature() {
        String fromUnit = spinnerTempFrom.getSelectedItem().toString();
        String toUnit = spinnerTempTo.getSelectedItem().toString();
        String input = editTextTemp.getText().toString();

        if (input.isEmpty()) {
            Toast.makeText(this, "Please enter a value!", Toast.LENGTH_SHORT).show();
            return;
        }

        double value = Double.parseDouble(input);
        double result = value;

        if (fromUnit.equals("Celsius") && toUnit.equals("Fahrenheit")) {
            result = (value * 1.8) + 32;
        } else if (fromUnit.equals("Fahrenheit") && toUnit.equals("Celsius")) {
            result = (value - 32) / 1.8;
        } else if (fromUnit.equals("Celsius") && toUnit.equals("Kelvin")) {
            result = value + 273.15;
        } else if (fromUnit.equals("Kelvin") && toUnit.equals("Celsius")) {
            result = value - 273.15;
        }

        textViewTempResult.setText("Result: " + result + " " + toUnit);
    }
}
