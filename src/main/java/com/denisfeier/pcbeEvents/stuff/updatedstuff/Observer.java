package com.denisfeier.pcbeEvents.stuff.updatedstuff;

import com.denisfeier.pcbeEvents.Entity.Person;
import com.denisfeier.pcbeEvents.stuff.updatedstuff.events.Event;

import java.util.Map;

public interface Observer {
    public void update(Event.Type type);
    public void subscribeToEvent(Event.Type type, Person.Filter filter, int value);
    public void unsubscribeFromEvent(Event event);
    public void updateSubscription(Event.Type type);
    public Map<Person.Filter,Integer> getEventFilter(Event.Type type);

    String getName();
}
