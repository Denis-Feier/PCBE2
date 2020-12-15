package com.denisfeier.pcbeEvents.events;

import com.denisfeier.pcbeEvents.events.handlers.*;

public class EventHandlerManager {
    public EventHandlerManager(){}
    public EventHandler getProperHandler(Event.Type type){
        switch (type){
            case NEW_DEMAND:return new newDemandEventHandler();
            case NEW_SUPPLY:return new newSupplyEventHandler();
            case MATCHED_SUPPLY_DEMAND:return new newMatchEventHandler();
            case NEW_SELLER:return new newSellerEventHandler();
            case NEW_BUYER:return new newBuyerEventHandler();
            case NEW_UPDATED_DEMAND:return new newUpdatedDemandEventHandler();
            case NEW_UPDATED_SUPPLY:return new newUpdatedSupplyEventHandler();
            default:return new BasicEventHandler() {
                @Override
                public boolean handle(Event event) {
                    return true;
                }
            };
        }
    }
}