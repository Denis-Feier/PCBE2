package com.denisfeier.pcbeEvents.Entity;

public class Seller extends Person {

    public Seller(String identifier, String name) {
        super(identifier, name);
    }

    public void createSupply(double price, int count) {

    }

    @Override
    public void notify(double cost) {
        System.out.println("Seller: " + super.getIdentifier() + " sold an action with " + cost);
    }


}