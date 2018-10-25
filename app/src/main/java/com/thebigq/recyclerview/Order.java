package com.thebigq.recyclerview;

public class Order {

    private String id;
    public String name;
    public int quantity;

    public Order(String id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
