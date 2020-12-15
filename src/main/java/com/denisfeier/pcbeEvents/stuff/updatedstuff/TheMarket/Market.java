package com.denisfeier.pcbeEvents.stuff.updatedstuff.TheMarket;

import com.denisfeier.pcbeEvents.Entity.Buyer;
import com.denisfeier.pcbeEvents.Entity.Demand;
import com.denisfeier.pcbeEvents.Entity.Person;
import com.denisfeier.pcbeEvents.Entity.Supply;
import com.denisfeier.pcbeEvents.stuff.updatedstuff.events.*;
import com.denisfeier.pcbeEvents.stuff.updatedstuff.events.events.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Market {
    private EventDispatcher dispatcher;
    private List<Demand> demands = Collections.synchronizedList(new ArrayList<>());
    private List <Supply> supplies = Collections.synchronizedList(new ArrayList<>());
    private List<Person> users = Collections.synchronizedList(new ArrayList<>());

    public void setDispatcher(EventDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public EventDispatcher getDispatcher() {
        return dispatcher;
    }

    public void addDemand(Demand demand){
        this.demands.add(demand);
        List<Object> content = new ArrayList<>();
        content.add(demand);
        this.dispatcher.addEvent(new NewDemandEvent(Event.Type.NEW_DEMAND,this,content));
        this.dispatcher.dispatch(Event.Type.NEW_DEMAND);
        this.matchDemandWithSupply(demand);
    }
    public void addSupply(Supply supply){
        this.supplies.add(supply);
        List<Object> content = new ArrayList<>();
        content.add(supply);
//        System.out.println("content in supply event : " + content);
        this.dispatcher.addEvent(new NewSupplyEvent(Event.Type.NEW_SUPPLY,this,content));
        this.dispatcher.dispatch(Event.Type.NEW_SUPPLY);
    }
    public void removeSupply(Supply supply){
            this.supplies.remove(supply);
    }
    public void removeDemand(Demand demand){
            this.demands.remove(demand);
    }
    public Demand retrieveDemand(Demand demand){
        for (Demand index : demands){
            if(demand.equals(index)){
                return index;
            }
        }
        return null;
    }
    public Supply retrieveSupply(Supply supply){
        for (Supply index : supplies){
            if(supply.equals(index)){
                return index;
            }
        }
        return null;
    }
    public List<Supply> getSupplies() {
        return supplies;
    }
    public Supply getSupply(String name){
        for(Supply s: supplies){
            if (s.getName().equals(name)){
                return s;
            }
        }
        return null;
    }
    public List<Demand> getDemands() {
        return demands;
    }

    public List<Person> getUsers() {
        return users;
    }
    public void updateDemandPrice(Demand demand, Double newPrice){
        this.dispatcher.addEvent(new NewUpdatedDemandEvent(Event.Type.NEW_UPDATED_DEMAND,this,new ArrayList<>()));
        this.dispatcher.dispatch(Event.Type.NEW_UPDATED_DEMAND);
            this.retrieveDemand(demand).setPrice(newPrice);
    }
    public void updateDemandCount(Demand demand, int count){
        this.dispatcher.addEvent(new NewUpdatedDemandEvent(Event.Type.NEW_UPDATED_DEMAND,this,new ArrayList<>()));
        this.dispatcher.dispatch(Event.Type.NEW_UPDATED_DEMAND);
            this.retrieveDemand(demand).setCount(count);
    }
    public void updateDemandOwner(Demand demand, Person owner){
        this.dispatcher.addEvent(new NewUpdatedDemandEvent(Event.Type.NEW_UPDATED_DEMAND,this,new ArrayList<>()));
        this.dispatcher.dispatch(Event.Type.NEW_UPDATED_DEMAND);
            this.retrieveDemand(demand).setOwner(owner);
    }
    public void updateSupplyPrice(Supply supply, Double newPrice){
        this.dispatcher.addEvent(new NewUpdatedSupplyEvent(Event.Type.NEW_UPDATED_SUPPLY,this,new ArrayList<>()));
        this.dispatcher.dispatch(Event.Type.NEW_UPDATED_SUPPLY);
            this.retrieveSupply(supply).setPrice(newPrice);
    }
    public void updateSupplyCount(Supply supply, int count){
        this.dispatcher.addEvent(new NewUpdatedSupplyEvent(Event.Type.NEW_UPDATED_SUPPLY,this,new ArrayList<>()));
        this.dispatcher.dispatch(Event.Type.NEW_UPDATED_SUPPLY);
            this.retrieveSupply(supply).setCount(count);
    }
    public void updateSupplyOwner(Supply supply, Person owner){
        this.dispatcher.addEvent(new NewUpdatedSupplyEvent(Event.Type.NEW_UPDATED_SUPPLY,this,new ArrayList<>()));
        this.dispatcher.dispatch(Event.Type.NEW_UPDATED_SUPPLY);
            this.retrieveSupply(supply).setOwner(owner);
    }
    public void addUser(Person person){
        if(person instanceof Buyer){
            this.dispatcher.addEvent(new NewBuyerEvent(Event.Type.NEW_BUYER,this,new ArrayList<>()));
            this.dispatcher.dispatch(Event.Type.NEW_BUYER);
        }
        else{
            this.dispatcher.addEvent(new NewSellerEvent(Event.Type.NEW_SELLER,this,new ArrayList<>()));
            this.dispatcher.dispatch(Event.Type.NEW_SELLER);
        }
        this.users.add(person);
    }

    public void TryBuy(Demand demand, Supply supply) {
            if(demand.getName() != supply.getName()){return;}
            if(demand.getPrice() <  supply.getPrice()){return;}
            if (supply.getCount() == 0 || demand.getCount() == 0) { return; }
            int min = Math.min(supply.getCount(), demand.getCount());
            System.out.println(demand.getName() + " :: " + "supply: " + supply.getCount() + " demand " + demand.getCount());
            List<Object> participants = new ArrayList<>();
            participants.add(demand.getOwner());
            participants.add(supply.getOwner());
            this.dispatcher.addEvent(new NewMatchEvent(Event.Type.MATCHED_SUPPLY_DEMAND,this,participants));
            dispatcher.dispatchTo(Event.Type.MATCHED_SUPPLY_DEMAND, supply.getOwner(),demand.getOwner());
            supply.setCount(supply.getCount() - min);
            demand.setCount(demand.getCount() - min);
            supply.use(min, supply.getPrice(), supply.getName());
            demand.use(min, supply.getPrice(), demand.getName());
    }
    public void matchDemandWithSupply(Demand demand) {
        int debounceWaitTime = 0;
        while (true) {
            debounceWaitTime++;
                    for (Supply value : this.supplies) {
                        if (value == null) {
                            return;
                        }
                        TryBuy(demand, value);
                        if (value.getCount() == 0) {
                            removeSupply(value);
                        }
                        if (demand.getCount() == 0) {
                            removeDemand(demand);
                            return;
                        }
                    }
            if (debounceWaitTime == 10) { return; }
        }
    }

    public void postMessage(String message){
        System.out.println("Message posted to : " + message);
    }
}
