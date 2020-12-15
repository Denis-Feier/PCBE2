package com.denisfeier.pcbeEvents.Entity;

import com.denisfeier.pcbeEvents.Observable;
import com.denisfeier.pcbeEvents.Observer;
import com.denisfeier.pcbeEvents.TheMarket.Market;
import com.denisfeier.pcbeEvents.events.Event;

import java.util.*;

public abstract class Person implements Observer {
    public enum Filter {
        GT,
        LS,
        EQ,
        DIF,
        NONE,
    }
    private String name;
    private Market market;

    private List<Observable> observables;

    private Map<Event.Type,HashMap<Filter,Integer>> subscription_Filters = new HashMap<>();

    private double budget;

    // bought stocks owned stocks, could be selling or buying
    private List<TradedItem> assets;

    public Person(String name, double budget, List<TradedItem> assets, Market market) {
        this.name = name;
        this.budget = budget;
        this.assets = assets;
        this.market = market;
        this.observables = Collections.synchronizedList(new ArrayList<>());
    }

    public Observable getObservable(Event.Type type){
        for(Observable o : observables){
            if(o.getType().equals(type)){
                return o;
            }
        }
        return null;
    }

    @Override
    public Map<Filter,Integer> getEventFilter(Event.Type type){
        return this.subscription_Filters.get(type);
    }

    public void addDemandToMarket(Demand demand){
                this.market.addDemand(demand);
    }
    public void addSupplyToMarket(Supply supply){
                this.market.addSupply(supply);
    }
    public Demand getDemandFromMarket(Demand demand){
        return this.market.retrieveDemand(demand);
    }
    public Supply getSupplyFromMarket(Supply supply){
        return this.market.retrieveSupply(supply);
    }

    public void updateSupplyPrice(Supply supply,Double newPrice){
                this.market.updateSupplyPrice(supply,newPrice);
    }
    public void updateSupplyCount(Supply supply, int newCount){
                this.market.updateSupplyCount(supply,newCount);
    }
    public void updateSupplyOwner(Supply supply,Person owner){
                this.market.updateSupplyOwner(supply,owner);
    }
    public void updateDemandPrice(Demand demand,Double newPrice){
                this.market.updateDemandPrice(demand,newPrice);
    }
    public void updateDemandCount(Demand demand, int newCount){
                this.market.updateDemandCount(demand,newCount);
    }
    public void updateDemandOwner(Demand demand,Person owner){
                this.market.updateDemandOwner(demand,owner);
    }

    @Override
    public void subscribeToEvent(Event.Type type, Person.Filter filter,int value){
        HashMap<Filter,Integer> myFilter = new HashMap<>();
        myFilter.put(filter,value);
        this.subscription_Filters.put(type,myFilter);
        this.market.getDispatcher().register(type,this);
        this.observables.add(market.getDispatcher().getEvent(type));
    }

    public void updateSubscription(Event.Type type){
        for(Observable o : observables){
            if(o.getType().equals(type)){
                observables.remove(o);
            }
        }
        this.observables.add(market.getDispatcher().getEvent(type));
    }

    @Override
    public void unsubscribeFromEvent(Event event){
        this.market.getDispatcher().getEvent(event.getType()).unregister(this);
    }

    @Override
    public void update(Event.Type type) {
        for(Observable o : observables){
            if (o.getObservers().contains(this) && o.getType() == type){
                List<Object> message = o.getUpdate(this);
                if(message == null) {
                    return;
                }else{
                    System.out.println(name + " :: " + message);
                }
            }
        }
    }
    public Market getMarket() {
        return market;
    }

    public void addAsset(TradedItem item){
        this.assets.add(item);
    }
    public TradedItem getAsset(TradedItem item){
        for (TradedItem i: assets){
            if(i.getIid() == item.getIid()){
                return i;
            }
        }
        return null;
    }
    public List<TradedItem> getAssets() {
        return assets;
    }

    public void setAssets(List<TradedItem> assets) {
        this.assets = assets;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getName() {
        return this.name;
    }

    public void sendMessage(String message){
        System.out.println("Hey there, I'm " + this.name);
    }

    public abstract void act(TradedItem item, int count);

}