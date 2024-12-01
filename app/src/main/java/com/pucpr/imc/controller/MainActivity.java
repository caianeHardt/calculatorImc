package com.pucpr.imc.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pucpr.imc.R;
import com.pucpr.imc.model.DataModel;

public class MainActivity extends AppCompatActivity {

    private EditText etWeight, etHeight, etAge, email;
    private RadioGroup rgGender;
    private RadioButton rbMale;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight = findViewById(R.id.etWeight);
        etHeight = findViewById(R.id.etHeight);
        etAge = findViewById(R.id.etAge);
        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        RadioButton rbFemale = findViewById(R.id.rbFemale);
        email = findViewById(R.id.email);
        Button btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    public void gotToSecondActivityButtonOnClick(View v){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//        intent.putExtra("name", "Mark");
        startActivity(intent);
    }

    public void gotToThirdActivityButtonOnClick(View v){
        String emailStr = email.getText().toString();
        if(!(emailStr.equals(DataModel.getInstance().userIMC.getEmail()))){
            Toast.makeText(this, "Você ainda não tem cadastro.", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
        startActivity(intent);
    }

    private void calculateBMI() {
        String weightStr = etWeight.getText().toString();
        String heightStr = etHeight.getText().toString();
        String ageStr = etAge.getText().toString();
        String emailStr = email.getText().toString();


        if (weightStr.isEmpty() || heightStr.isEmpty() || ageStr.isEmpty() || rgGender.getCheckedRadioButtonId() == -1 || emailStr.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }


        double weight = Double.parseDouble(weightStr);
        double heightCm = Double.parseDouble(heightStr);
        double heightM = heightCm / 100.0;
        int age = Integer.parseInt(ageStr);
        double bmi = weight / (heightM * heightM);

        String result;
        if (rbMale.isChecked()) {
            result = "IMC para homem (idade " + age + "): " + String.format("%.2f", bmi) + "\n";
            if (bmi < 18.5) {
                result += "Magreza";
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                result += "Normal";
            } else if (bmi > 24.9 && bmi <= 30) {
                result += "Sobrepeso";
            } else {
                result += "Obesidade";
            }
        } else {
            result = "IMC para mulher (idade " + age + "): " + String.format("%.2f", bmi) + "\n";
            if (bmi < 18.5) {
                result += "Magreza";
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                result += "Normal";
            } else if (bmi > 24.9 && bmi <= 30) {
                result += "Sobrepeso";
            } else {
                result += "Obesidade";
            }
        }
        tvResult.setText(result);
    }
}
