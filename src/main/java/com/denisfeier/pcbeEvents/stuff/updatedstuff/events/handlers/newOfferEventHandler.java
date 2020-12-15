package com.denisfeier.pcbeEvents.stuff.updatedstuff.events.handlers;

import com.denisfeier.pcbeEvents.stuff.updatedstuff.events.Event;
import com.denisfeier.pcbeEvents.stuff.updatedstuff.events.EventHandler;

public class newOfferEventHandler extends BasicEventHandler{

    @Override
    public boolean handle(Event event) {
        System.out.println("New offer!");
        return true;
    }
}
