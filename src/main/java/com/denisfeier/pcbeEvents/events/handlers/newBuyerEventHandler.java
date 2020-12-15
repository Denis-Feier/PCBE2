package com.denisfeier.pcbeEvents.events.handlers;

import com.denisfeier.pcbeEvents.Entity.Person;
import com.denisfeier.pcbeEvents.events.Event;

public class newBuyerEventHandler extends BasicEventHandler {
    @Override
    public boolean handle(Event event) {
        System.out.println("\nNew Buyer added to market");
        event.setChanged(true);
        event.notifyObservers();
        return true;
    }
    @Override
    public boolean handleLocally(Event event, Person p1, Person p2) {
        System.out.println("\nNew Buyer added to market");
        event.setChanged(true);
        event.notifyParticipants(p1,p2);
        return true;
    }
}
