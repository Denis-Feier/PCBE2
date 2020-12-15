package com.denisfeier.pcbeEvents.TheMarket;

import com.denisfeier.pcbeEvents.events.EventDispatcher;

public class MarketSingleton {
    private static final Market market = getSingletonInstance();
    public static Market getInstance(){
        return market;
    }
    private static Market getSingletonInstance(){
        return new MarketBuilder().setDispatcher(new EventDispatcher()).build();
    }
}
