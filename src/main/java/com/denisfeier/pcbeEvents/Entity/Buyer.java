package com.denisfeier.pcbeEvents.Entity;

public class Buyer extends Person {

    public Buyer(String identifier, String name) {
        super(identifier, name);
    }

    @Override
    void notify(double cost) {
        System.out.println("Buyer: " + super.getIdentifier() + " bought an action with " + cost);
    }

//    public void addDemand(final Demand demand) {
//        final Buyer self = this;
//        new Thread(new Runnable() {
//            public void run() {
//                synchronized (self.stockMarket) {
//                    self.stockMarket.addDemand(demand);
//                }
//                Thread.currentThread().interrupt();
//            }
//        }).start();
    }
}