package com.denisfeier.pcbeEvents.events.events;

import com.denisfeier.pcbeEvents.Entity.Supply;
import com.denisfeier.pcbeEvents.Observer;
import com.denisfeier.pcbeEvents.TheMarket.Market;
import com.denisfeier.pcbeEvents.events.Event;

import java.util.ArrayList;
import java.util.List;

public class NewUpdatedSupplyEvent extends Event {
    public NewUpdatedSupplyEvent(Event.Type type, Market market, List<Object> content) {
        super(type, market,content);
    }
    @Override
    public List<Object> getUpdate(Observer observer) {
        List<Supply> last = getMarket().getSupplies();
        int listSize = last.size();
        Supply lastSupply = last.get(listSize - 1);
        List<Object> ar = new ArrayList<>();
        ar.add(lastSupply.getName());
        return ar;
    }
}
