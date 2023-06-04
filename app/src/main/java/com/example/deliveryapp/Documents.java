package com.example.deliveryapp;

public class Documents extends Package{

    public Documents(String from, String to) {
        super(from, to);
        this.fragility = false;
        this.from = from;
        this.to = to;
    }


    //геттеры
    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getType() {
        return "Д";
    }
}
