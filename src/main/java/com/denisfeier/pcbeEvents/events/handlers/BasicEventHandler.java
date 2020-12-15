package com.denisfeier.pcbeEvents.events.handlers;

import com.denisfeier.pcbeEvents.Entity.Person;
import com.denisfeier.pcbeEvents.events.Event;
import com.denisfeier.pcbeEvents.events.EventHandler;

public abstract class BasicEventHandler implements EventHandler {
    @Override
    public void notifyObservers(Event event) {
        event.setChanged(true);
        event.notifyObservers();
    }
    @Override
    public abstract boolean handle(Event event);
    @Override
    public boolean handleLocally(Event event, Person p1, Person p2){
        return true;
    }
}
