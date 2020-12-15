package com.denisfeier.pcbeEvents.stuff.updatedstuff.events.handlers;

import com.denisfeier.pcbeEvents.Entity.Person;
import com.denisfeier.pcbeEvents.stuff.updatedstuff.events.Event;

public class newUpdatedSupplyEventHandler extends BasicEventHandler{
    @Override
    public boolean handle(Event event) {
        System.out.println("\nA supply has been modified");
        event.setChanged(true);
        event.notifyObservers();
        return true;
    }

    @Override
    public boolean handleLocally(Event event, Person p1, Person p2) {
        return true;
    }
}
