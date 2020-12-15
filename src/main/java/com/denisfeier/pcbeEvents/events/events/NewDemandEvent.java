package com.denisfeier.pcbeEvents.events.events;

import com.denisfeier.pcbeEvents.Entity.Demand;
import com.denisfeier.pcbeEvents.Observer;
import com.denisfeier.pcbeEvents.TheMarket.Market;
import com.denisfeier.pcbeEvents.events.Event;

import java.util.ArrayList;
import java.util.List;

public class NewDemandEvent extends Event {
        public NewDemandEvent(Event.Type type, Market market, List<Object> content) {
            super(Type.NEW_DEMAND, market,content);
        }
        @Override
        public List<Object> getUpdate(Observer observer) {
//            return this.getContent();
            List<Demand> last = getMarket().getDemands();
            int listSize = last.size();
            Demand lastDemand = last.get(listSize - 1);
            List<Object> ar = new ArrayList<>();
            ar.add(lastDemand.getName());
            return ar;
//            return lastDemand.getName() + "| count: " + lastDemand.getCount() + " price: " + lastDemand.getPrice();
        }
}

