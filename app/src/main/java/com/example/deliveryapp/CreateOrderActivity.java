package com.example.deliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateOrderActivity extends AppCompatActivity {

    private String[] types = {"Д", "М", "Б"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        ArrayAdapter<String> typesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);
        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(typesAdapter);
    }

    public void onCreateOrder(View v) {

        Intent intent = new Intent(this, ChooseParamsActivity.class);

        String company = ((EditText)findViewById(R.id.inputCompany)).getText().toString();
        String type = ((Spinner)findViewById(R.id.spinner)).getSelectedItem().toString();
        String from = ((EditText)findViewById(R.id.inputFrom)).getText().toString();
        String to = ((EditText)findViewById(R.id.inputTo)).getText().toString();
        double cost = Double.parseDouble(((EditText)findViewById(R.id.inputCost)).getText().toString());
        boolean fragile = ((CheckBox)findViewById(R.id.checkBoxFragile)).isChecked();

        intent.putExtra("company", company);
        intent.putExtra("type", type);
        intent.putExtra("from", from);
        intent.putExtra("to", to);
        intent.putExtra("cost", cost);
        intent.putExtra("fragile", fragile);

        startActivity(intent);
    }
}