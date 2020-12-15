package com.denisfeier.pcbeEvents.Entity;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class TradedItem {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    private int iid;
    private String name;
    private int count;
    private double price;
    private Person owner;

    public TradedItem(String name, int count, double price, Person owner) {
        this.iid = ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.count = count;
        this.price = price;
        this.owner = owner;
    }

    public int getIid() {
        return iid;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public void use(int count, double price, String name) {
        new Thread(()->{
            synchronized (this.owner.getMarket()){
                int remaining = this.getCount() - count;
                this.setCount(remaining);
                this.getOwner().act(this,remaining);
            }
        }).start();
    }
}