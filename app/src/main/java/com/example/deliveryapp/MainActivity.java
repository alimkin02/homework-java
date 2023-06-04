package com.example.deliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // стартовое окно с инфой о курьере (чекбокс есть ли машина и тд)
    // класс Menu (создание заказов)

    //курьер
    Courier courier = new Courier("Алимкин Александр Кириллович", "231231");

    //посылки
    SmallPackage vase = new SmallPackage("10*10*40", true, "Томск", "Москва");
    SmallPackage bag = new SmallPackage("30*40*50", false, "Самара", "Сочи");
    Documents docs = new Documents("Москва", "Омск");
    BigPackage babyCarriage = new BigPackage("100*70*80", false, 10, "Москва", "СПБ");

    //компании
    Company yandex = new Company("Yandex", "Москва, ул. Льва Толстого, 16");
    Company google = new Company("Google", "America");

    //заказы
    Order order1 = new Order(yandex, vase, vase.getFrom(), vase.getTo(), 1200);
    Order order2 = new Order(yandex, bag, bag.getFrom(), bag.getTo(), 1500);
    Order order3 = new Order(google, docs, docs.getFrom(), docs.getTo(), 1600);
    Order order4 = new Order(google, babyCarriage, babyCarriage.getFrom(), babyCarriage.getTo(), 3500);
    Order order5 = new Order(google, babyCarriage, babyCarriage.getFrom(), babyCarriage.getTo(), 3500);
    Order order6 = new Order(google, babyCarriage, babyCarriage.getFrom(), babyCarriage.getTo(), 3500);
    Order order7 = new Order(google, babyCarriage, babyCarriage.getFrom(), babyCarriage.getTo(), 3500);
    Order order8 = new Order(google, babyCarriage, babyCarriage.getFrom(), babyCarriage.getTo(), 3500);
    Order order9 = new Order(google, babyCarriage, babyCarriage.getFrom(), babyCarriage.getTo(), 3500);
    Order order10 = new Order(google, babyCarriage, babyCarriage.getFrom(), babyCarriage.getTo(), 3500);
    Order order11 = new Order(google, babyCarriage, babyCarriage.getFrom(), babyCarriage.getTo(), 3500);
    Order order12 = new Order(google, babyCarriage, babyCarriage.getFrom(), babyCarriage.getTo(), 3500);
    Order order13 = new Order(google, babyCarriage, babyCarriage.getFrom(), babyCarriage.getTo(), 3500);

    ArrayList<Order> orders = new ArrayList<Order>();
    ArrayList<Order> courierOrders = courier.getOrders();

    private ListView listView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    public void goToCreate(MenuItem item) {

        Intent intent = new Intent(this, CreateOrderActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {

        super.onStart();
        courier.getOrders().clear();

        TextView courier_name = findViewById(R.id.courier_name);
        courier_name.setText(courier.getFIO());

        boolean isDocs = getIntent().getBooleanExtra("isDocs", true);
        boolean isAuto = getIntent().getBooleanExtra("isAuto", true);
        boolean isFragile = getIntent().getBooleanExtra("isFragile", true);

        String company = null;
        if (getIntent().hasExtra("company")) {
            company = getIntent().getStringExtra("company");
        }
        String type = null;
        if (getIntent().hasExtra("type")) {
            type = getIntent().getStringExtra("type");
        }
        String from = null;
        if (getIntent().hasExtra("from")) {
            from = getIntent().getStringExtra("from");
        }
        String to = null;
        if (getIntent().hasExtra("to")) {
            to = getIntent().getStringExtra("to");
        }
        double cost = 0;
        if (getIntent().hasExtra("cost")) {
            cost = getIntent().getDoubleExtra("cost", 1080);
        }

        boolean fragile = false;
        if (getIntent().hasExtra("fragile")) {
            fragile = getIntent().getBooleanExtra("fragile", false);
        }

        if (company != null) {
            assert type != null;
            if (type.equals("Д")) {
                Package pack = new Documents(from, to);
                Order order = new Order(new Company(company, "Moscow, Tverskaya"), pack, from, to, cost);
                orders.add(order);
            } else if (type.equals("М")) {
                Package pack = new SmallPackage("10x10", fragile, from, to);
                Order order = new Order(new Company(company, "Moscow, Tverskaya"), pack, from, to, cost);
                orders.add(order);
            } else {
                Package pack = new BigPackage("100х100", fragile, 100, from, to);
                Order order = new Order(new Company(company, "Moscow, Tverskaya"), pack, from, to, cost);
                orders.add(order);
            }
        }



        for (int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getPack().getType().equals("Д")) {
                if (isDocs) {
                    courier.addOrder(orders.get(i));
                }
            } else if (orders.get(i).getPack().isFragility()) {
                if (isFragile) {
                    courier.addOrder(orders.get(i));
                }
            } else if (orders.get(i).getPack().getType().equals("Б")) {
                if (isAuto) {
                    courier.addOrder(orders.get(i));
                }
            } else {
                courier.addOrder(orders.get(i));
            }
        }

        OrderAdapter adapter = new OrderAdapter(this, courierOrders);
        listView.setAdapter(adapter);

        Button btn_ok = findViewById(R.id.button_ok);
        Button btn_clear = findViewById(R.id.button_clear);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double result = 0;
                for (int i = 0; i < adapter.getOrders().size(); i++) {
                    if (adapter.getOrders().get(i).isSelected()) {
                        result += Double.parseDouble(adapter.getOrders().get(i).getCost());
                    }
                }
                showInfo(result);
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < adapter.getOrders().size(); i++) {
                    adapter.getOrders().get(i).setSelected(false);
                }
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);
        orders.add(order5);
        orders.add(order6);
        orders.add(order7);
        orders.add(order8);
        orders.add(order9);
        orders.add(order10);
        orders.add(order11);
        orders.add(order12);
        orders.add(order13);
        listView = findViewById(R.id.listView);

    }

    private void showInfo(double cost) {
        Toast.makeText(this, "Общая стоимость: " + cost, Toast.LENGTH_LONG).show();
    }



}