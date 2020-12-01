package com.denisfeier.pcbeEvents;

import com.denisfeier.pcbeEvents.Events.BuyerFoundEvent;
import com.denisfeier.pcbeEvents.Events.ModifyStockEvent;
import com.denisfeier.pcbeEvents.Events.NewStockEvent;
import com.denisfeier.pcbeEvents.Lib.Event;
import com.denisfeier.pcbeEvents.Lib.EventDispatcher;
import com.denisfeier.pcbeEvents.Lib.Handler;
import com.denisfeier.pcbeEvents.market.MarketState;

public class Main {

    public static void main(String[] args) {
        EventDispatcher dispatcher = new EventDispatcher();
        MarketState marketState = new MarketState(dispatcher);

        dispatcher.registerChannel(BuyerFoundEvent.class, new Handler() {
            @Override
            public void dispatch(Event message) {

            }
        });

        dispatcher.registerChannel(ModifyStockEvent.class, new Handler() {
            @Override
            public void dispatch(Event message) {

            }
        });

        dispatcher.registerChannel(NewStockEvent.class, new Handler() {
            @Override
            public void dispatch(Event message) {

            }
        });
    }
}
