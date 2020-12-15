package com.denisfeier.pcbeEvents.events.handlers;

import com.denisfeier.pcbeEvents.Entity.Person;
import com.denisfeier.pcbeEvents.events.Event;

public class newDemandEventHandler extends BasicEventHandler {
    @Override
    public boolean handle(Event event) {
        System.out.println("\nA demand has been added to the market");
        event.setChanged(true);
        event.notifyObservers();
        return true;
    }
    @Override
    public boolean handleLocally(Event event, Person p1, Person p2) {
        return true;
    }
}
