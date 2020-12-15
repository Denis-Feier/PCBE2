package com.denisfeier.pcbeEvents.events.events;

import com.denisfeier.pcbeEvents.Entity.Person;
import com.denisfeier.pcbeEvents.Observer;
import com.denisfeier.pcbeEvents.TheMarket.Market;
import com.denisfeier.pcbeEvents.events.Event;

import java.util.ArrayList;
import java.util.List;

public class NewBuyerEvent extends Event {
    public NewBuyerEvent(Event.Type type, Market market, List<Object> content) {
        super(Type.NEW_BUYER, market,content);
    }
    @Override
    public List<Object> getUpdate(Observer observer) {
//        return this.getContent();
        List<Person> last = getMarket().getUsers();
        int listSize = last.size();
        Person lastPerson = last.get(listSize - 1);
        List<Object> ar = new ArrayList<>();
        ar.add(lastPerson.getName());
        return ar;
//        return lastPerson.getName() + "| with assets: " + lastPerson.getAssets();
    }
}
