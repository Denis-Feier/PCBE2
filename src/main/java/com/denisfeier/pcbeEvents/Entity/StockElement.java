package com.denisfeier.pcbeEvents.Entity;

import lombok.Data;

@Data
public abstract class StockElement {
    private double price;
    private int count;
    private Person owner;

    StockElement(double price, int count, Person owner) {
        this.setPrice(price);
        this.setCount(count);
        this.setOwner(owner);
    }

    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    void setCount(int count) {
        this.count = count;
    }

    public Person getOwner() {
        return owner;
    }

    private void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "{\"price\": " + price + ", \"count\": " + count + "\"owner\": " + owner.getName() + "}";
    }


}