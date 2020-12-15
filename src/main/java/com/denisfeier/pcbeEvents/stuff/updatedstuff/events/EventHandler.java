package com.denisfeier.pcbeEvents.stuff.updatedstuff.events;

import com.denisfeier.pcbeEvents.Entity.Person;

public interface EventHandler {
    public void notifyObservers(Event event);
    public boolean handle(Event event);
    public boolean handleLocally(Event event, Person p1, Person p2);
}
