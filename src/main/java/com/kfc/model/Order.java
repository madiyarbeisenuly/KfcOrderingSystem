package com.kfc.model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Product> items;
    private double total;

    public Order(List<Product> items) {
        this.items = items;
        this.total = items.stream().mapToDouble(Product::getPrice).sum();
    }

    public List<Product> getItems() {   // <-- ЭТОТ МЕТОД ДОЛЖЕН БЫТЬ
        return items;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Заказ на сумму: " + total + " ₸";
    }
}