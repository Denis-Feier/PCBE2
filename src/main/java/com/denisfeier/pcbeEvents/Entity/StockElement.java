package com.denisfeier.pcbeEvents.Entity;

import lombok.Data;

@Data
public abstract class StockElement {
    private String id;
    private double price;
    private Person owner;

    StockElement(double price, Person owner) {
        this.setPrice(price);
        this.setOwner(owner);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Person getOwner() {
        return owner;
    }

    private void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "{\"price\": " + price + " \"owner\": " + owner.getName() + "}";
    }


}