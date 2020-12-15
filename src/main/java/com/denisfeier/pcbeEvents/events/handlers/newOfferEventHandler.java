package com.denisfeier.pcbeEvents.events.handlers;

import com.denisfeier.pcbeEvents.events.Event;

public class newOfferEventHandler extends BasicEventHandler{

    @Override
    public boolean handle(Event event) {
        System.out.println("New offer!");
        return true;
    }
}
