package com.denisfeier.pcbeEvents.TheMarket;

import com.denisfeier.pcbeEvents.events.EventDispatcher;

public class MarketBuilder {

    private Market market;

    private EventDispatcher dispatcher = new EventDispatcher();

    MarketBuilder(){
        this.market = new Market();
        this.market.setDispatcher(dispatcher);
    }

    MarketBuilder setDispatcher(EventDispatcher dispatcher){
        this.market.setDispatcher(dispatcher.configureDispatcher(market));
        return this;
    }

    Market build(){
        return this.market;
    }

}
