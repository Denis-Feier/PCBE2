package com.denisfeier.pcbeEvents.Entity;

import com.denisfeier.pcbeEvents.TheMarket.Market;

import java.util.List;

public class Buyer extends Person {

    public Buyer(String name, double budget, List<TradedItem> assets, Market market) {
        super(name,budget,assets,market);
    }

    @Override
    public void act(TradedItem item, int count) {
        if(this.getAssets().contains(item)){
            this.getAsset(item).setCount(this.getAsset(item).getCount() + count);
        }
    }
}