package com.denisfeier.pcbeEvents.events.events;

import com.denisfeier.pcbeEvents.Observer;
import com.denisfeier.pcbeEvents.TheMarket.Market;
import com.denisfeier.pcbeEvents.events.Event;

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
