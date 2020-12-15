package com.denisfeier.pcbeEvents.stuff.updatedstuff.events.events;

import com.denisfeier.pcbeEvents.stuff.updatedstuff.Observer;
import com.denisfeier.pcbeEvents.stuff.updatedstuff.TheMarket.Market;
import com.denisfeier.pcbeEvents.stuff.updatedstuff.events.Event;

import java.util.ArrayList;
import java.util.List;

public class NewMatchEvent extends Event {

    public NewMatchEvent(Type type, Market market, List<Object> content) {
        super(Type.MATCHED_SUPPLY_DEMAND, market,content);
    }

    @Override
    public List<Object> getUpdate(Observer observer) {
//        return this.getContent();
        List<Object> ar = new ArrayList<>();
        ar.add("New Match Event");
        return ar;
//                return "New match event";
    }
}
