package com.denisfeier.pcbeEvents.stuff.updatedstuff.events.events;

import com.denisfeier.pcbeEvents.Entity.Supply;
import com.denisfeier.pcbeEvents.Entity.TradedItem;
import com.denisfeier.pcbeEvents.stuff.updatedstuff.TheMarket.Market;
import com.denisfeier.pcbeEvents.stuff.updatedstuff.Observer;
import com.denisfeier.pcbeEvents.stuff.updatedstuff.events.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NewSupplyEvent extends Event {

    public NewSupplyEvent(Type type, Market market, List<Object> content) {
        super(Type.NEW_SUPPLY, market,content);
//        this.setSupplies(supplies);
    }

    @Override
    public List<Object> getUpdate(Observer observer) {
//        return this.getContent();
        List<Supply> last = getMarket().getSupplies();
        int listSize = last.size();
        Supply lastSupply = last.get(listSize - 1);
        List<Object> ar = new ArrayList<>();
        ar.add(lastSupply.getName());
        return ar;
//        return lastSupply.getName() + "| count: " + lastSupply.getCount() + " price: " + lastSupply.getPrice();
    }
}
