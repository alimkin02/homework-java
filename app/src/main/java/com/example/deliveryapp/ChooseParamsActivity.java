package com.example.deliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class ChooseParamsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_params);

    }

    public void goToOrders(View v) {

        Intent intent = new Intent(this, MainActivity.class);
        boolean isDocs = ((CheckBox)findViewById(R.id.checkBoxDocs)).isChecked();
        boolean isAuto = ((CheckBox)findViewById(R.id.checkBoxAuto)).isChecked();
        boolean isFragile = ((CheckBox)findViewById(R.id.checkBoxFragile)).isChecked();

        intent.putExtra("isDocs", isDocs);
        intent.putExtra("isAuto", isAuto);
        intent.putExtra("isFragile", isFragile);


        String company;
        String type;
        String from;
        String to;
        double cost;
        boolean fragile;

        if (getIntent().hasExtra("company")) {
            company = getIntent().getStringExtra("company");
            intent.putExtra("company", company);
        }
        if (getIntent().hasExtra("type")) {
            type = getIntent().getStringExtra("type");
            intent.putExtra("type", type);
        }
        if (getIntent().hasExtra("from")) {
            from = getIntent().getStringExtra("from");
            intent.putExtra("from", from);
        }
        if (getIntent().hasExtra("to")) {
            to = getIntent().getStringExtra("to");
            intent.putExtra("to", to);
        }
        if (getIntent().hasExtra("cost")) {
            cost = getIntent().getDoubleExtra("cost", 1000);
            intent.putExtra("cost", cost);
        }
        if (getIntent().hasExtra("fragile")) {
            fragile = getIntent().getBooleanExtra("fragile", false);
            intent.putExtra("fragile", fragile);
        }

        startActivity(intent);
    }
}