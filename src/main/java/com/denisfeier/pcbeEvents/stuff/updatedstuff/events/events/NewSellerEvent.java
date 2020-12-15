package com.denisfeier.pcbeEvents.stuff.updatedstuff.events.events;

import com.denisfeier.pcbeEvents.Entity.Person;
import com.denisfeier.pcbeEvents.stuff.updatedstuff.Observer;
import com.denisfeier.pcbeEvents.stuff.updatedstuff.TheMarket.Market;
import com.denisfeier.pcbeEvents.stuff.updatedstuff.events.Event;

import java.util.ArrayList;
import java.util.List;

public class NewSellerEvent extends Event {
    public NewSellerEvent(Event.Type type, Market market, List<Object> content) {
        super(Type.NEW_SELLER, market,content);
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
