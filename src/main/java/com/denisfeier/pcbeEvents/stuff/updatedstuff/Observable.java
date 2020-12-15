package com.denisfeier.pcbeEvents.stuff.updatedstuff;

import com.denisfeier.pcbeEvents.Entity.Person;
import com.denisfeier.pcbeEvents.stuff.updatedstuff.events.Event;

import java.util.List;

public interface Observable {

    public Event.Type getType();
    public void register(Observer observer);
    public void unregister(Observer observer);
    public List<Observer> getObservers();
    public void notifyObservers();
    public List<Object> getContent();
    public void notifyParticipants(Person p1, Person p2);
    public List<Object> getUpdate(Observer observer);
}
