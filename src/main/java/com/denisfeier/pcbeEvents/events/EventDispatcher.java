package com.denisfeier.pcbeEvents.events;

import com.denisfeier.pcbeEvents.Entity.Person;
import com.denisfeier.pcbeEvents.Observer;
import com.denisfeier.pcbeEvents.TheMarket.Market;
import com.denisfeier.pcbeEvents.events.events.*;

import java.util.*;

public class EventDispatcher {
    private Map<Event.Type, EventHandler> events;
    public List<Event> getOccured() {
        return occured;
    }
    public List<Event> getAwaiting() {
        return awaiting;
    }

    private List<Event> occured;
    private List<Event> awaiting;
    private EventHandlerManager eventHandlerManager = new EventHandlerManager();

    public EventDispatcher(){
        this.events = new HashMap<>();
        this.occured = new ArrayList<>();
        this.awaiting = new ArrayList<>();
    }

    public EventDispatcher configureDispatcher(Market market){
        Event newDemandEvent = new NewDemandEvent(Event.Type.NEW_DEMAND,market,new ArrayList<>());
        Event newSupplyEvent = new NewSupplyEvent(Event.Type.NEW_SUPPLY,market,new ArrayList<>());
        Event newMatchEvent = new NewMatchEvent(Event.Type.MATCHED_SUPPLY_DEMAND,market,new ArrayList<>());
        Event newBuyerEvent = new NewBuyerEvent(Event.Type.NEW_BUYER,market,new ArrayList<>());
        Event newSellerEvent = new NewSellerEvent(Event.Type.NEW_SELLER,market,new ArrayList<>());
        Event newUpdatedSupply = new NewUpdatedSupplyEvent(Event.Type.NEW_UPDATED_SUPPLY,market,new ArrayList<>());
        Event newUpdatedDemand = new NewUpdatedDemandEvent(Event.Type.NEW_UPDATED_DEMAND,market,new ArrayList<>());
        this.registerEvent(newDemandEvent);
        this.registerEvent(newSupplyEvent);
        this.registerEvent(newMatchEvent);
        this.registerEvent(newBuyerEvent);
        this.registerEvent(newSellerEvent);
        this.registerEvent(newUpdatedSupply);
        this.registerEvent(newUpdatedDemand);
        return this;
    }

    public void registerEvent(Event event) {
        if(this.events.containsKey(event.getType())){
            System.out.println("already has key");
            return;
        }
        for (Event index: awaiting){
            if(index.getType() == event.getType()){
                System.out.println("event already registered");
                return;
            }
        }
        this.awaiting.add(event);
        this.events.put(event.getType(),eventHandlerManager.getProperHandler(event.getType()));
    }

    public void register(Event.Type type, Observer observer){
        if(!this.events.containsKey(type)){
            System.out.println("no event type");
            return;
        }
        for(Event index : this.awaiting){
            if(index.getType() == type){
                index.register(observer);
                return;
            }
        }
    }

    public void dispatchTo(Event.Type type, Person person1, Person person2){
        if(!this.events.containsKey(type)){
            return;
        }
        for(Event index : awaiting){
            if (index.getType() == type){
                if(index.isHandled()){
                    System.out.println("Already handled");
                    this.occured.add(index);
                    this.awaiting.remove(index);
                    return;
                }
                boolean isHandled;
                if(person1 == null && person2 == null){
                    isHandled = events.get(type).handle(index);
                }else{
                    isHandled = events.get(type).handleLocally(index,person1,person2);
                }
                if(isHandled){
                    this.occured.add(index);
                    this.awaiting.remove(index);
                }
                index.setHandled(isHandled);
                return;
            }
        }
    }

    public void dispatch(Event.Type type){
        dispatchTo(type,null,null);
    }

    public Event getEvent(Event.Type type){
        for(Event index: awaiting){
            if(index.getType().equals(type)){
                return index;
            }
        }
        return null;
    }

    public Event getOccured(Event.Type type){
        for(Event index: occured){
            if(index.getType().equals(type)){
                return index;
            }
        }
        return null;
    }
    public void addEvent(Event event){
        Event inAwaiting = getEvent(event.getType());
        Event inOccured = getOccured(event.getType());

        if(inAwaiting!=null){
            for(Observer o : inAwaiting.getObservers()){
                event.register(o);
            }
        }
        if(inOccured!=null){
            for(Observer o : inOccured.getObservers()){
                event.register(o);
            }
        }
        this.awaiting.add(event);
    }
}
