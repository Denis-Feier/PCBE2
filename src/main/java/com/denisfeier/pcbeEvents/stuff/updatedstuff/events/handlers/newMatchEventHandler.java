package com.denisfeier.pcbeEvents.stuff.updatedstuff.events.handlers;

import com.denisfeier.pcbeEvents.Entity.Person;
import com.denisfeier.pcbeEvents.stuff.updatedstuff.events.Event;
import com.denisfeier.pcbeEvents.stuff.updatedstuff.events.EventHandler;

public class newMatchEventHandler extends BasicEventHandler {

    @Override
    public boolean handle(Event event) {
        System.out.println("\nMatched offer with demand");
        event.setChanged(true);
//        System.out.println(event.getObservers());
//        System.out.println(event.getType());
        event.notifyObservers();
        return true;
    }

    @Override
    public boolean handleLocally(Event event, Person p1, Person p2) {
        System.out.println("\nMatched offer with demand");
        event.setChanged(true);
        event.notifyParticipants(p1,p2);
        return true;
    }
}
