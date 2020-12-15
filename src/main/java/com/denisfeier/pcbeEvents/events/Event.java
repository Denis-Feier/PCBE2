package com.denisfeier.pcbeEvents.events;

import com.denisfeier.pcbeEvents.Entity.Person;
import com.denisfeier.pcbeEvents.TheMarket.Market;
import com.denisfeier.pcbeEvents.Observable;
import com.denisfeier.pcbeEvents.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Event implements Observable{
    private final List<Observer> observers;
    public enum Type {
        NEW_SUPPLY,
        NEW_DEMAND,
        MATCHED_SUPPLY_DEMAND,
        NEW_BUYER,
        NEW_SELLER,
        NEW_UPDATED_SUPPLY,
        NEW_UPDATED_DEMAND,
        }
    private List<Object> content;
    private Type type;
    private Market market;
    private boolean handled;
    private boolean changed;
    private final Object MUTEX=new Object();

    public Event(Type type, Market market,List<Object> content){
        this.market = market;
        this.type = type;
        this.content = content;
        this.handled = false;
        this.observers = new ArrayList<>();
    }

    public List<Observer> getObservers() {
        return this.observers;
    }

    public Type getType(){
        return this.type;
    };

    public boolean isHandled(){
        return this.handled;
    }
    public boolean setHandled(boolean handleStatus){
        this.handled = handleStatus;
        return this.handled;
    };

    @Override
    public List<Object> getContent() {
        return this.content;
    }

    @Override
    public void register(Observer observer) {
        if(observer == null) throw new NullPointerException("Null listener");
        synchronized (MUTEX){
            if(!observers.contains(observer)) observers.add(observer);
        }
    }

    @Override
    public void unregister(Observer observer) {
        synchronized (MUTEX){
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        List<Observer> localObservers = null;
        synchronized (MUTEX){
            if(!changed) {
                return;
            };
            localObservers = new ArrayList<>(this.observers);
            this.changed = false;
        }
        for(Observer observer : localObservers){
            observer.update(this.getType());
        }
    }

    public void notifyParticipants(Person p1, Person p2) {
        Observer person1 = null;
        Observer person2 = null;
        boolean alreadySetPerson1 = false;
        boolean alreadySetPerson2 = false;
        synchronized (MUTEX){
            if(!changed) {
                return;
            };
            for(Observer o: observers){
                if(!alreadySetPerson1 && o.equals(p1)){
                    person1 = o;
                    alreadySetPerson1 = true;
                }
                if(!alreadySetPerson2 && o.equals(p2) ){
                    person2 = o;
                    alreadySetPerson2 = true;
                }
            }
            this.changed = false;
        }
        if (person1 != null) {
            person1.update(this.getType());
        }
        if (person2 != null) {
            person2.update(this.getType());
        }
    }

    @Override
    public abstract List<Object> getUpdate(Observer observer);

    public void setChanged(boolean changed){
        this.changed = changed;
    }
    public boolean getChanged(){
        return this.changed;
    }

    public Market getMarket(){
        return this.market;
    }
}
