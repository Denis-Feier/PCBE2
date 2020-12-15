package com.denisfeier.pcbeEvents.stuff.updatedstuff;

import com.denisfeier.pcbeEvents.ChatEventMachine;
import com.denisfeier.pcbeEvents.Entity.Buyer;
import com.denisfeier.pcbeEvents.Entity.Person;
import com.denisfeier.pcbeEvents.Entity.Seller;
import com.denisfeier.pcbeEvents.Lib.EventDispatcher;

import java.util.ArrayList;

public class marketState {
    private EventDispatcher dispatcher;
    private ArrayList<Buyer> buyers;
    private ArrayList<Seller> sellers;

//    private ArrayList<ChatEventMachine.User> users;

    public marketState(EventDispatcher dispatcher){
        this.dispatcher =dispatcher;
        this.buyers = new ArrayList<Buyer>();
        this.sellers = new ArrayList<Seller>();
    }

    public void broadcast(Buyer buyer, Seller seller, String message) {
        for (Buyer recipient : buyers) {
            if (!recipient.equals(buyer))
                recipient.sendMessage(message);
        }
        for (Seller recipient : sellers) {
            if (!recipient.equals(seller))
                recipient.sendMessage(message);
        }
    }
    public void sendTo(Person sender, Person recipient,String message){
        System.out.println("something");
    }


    public void addBuyer(Buyer buyer) {
        buyers.add(buyer);
    }
    public void addSeller(Seller seller) {
        sellers.add(seller);
    }

    public void removeBuyer(Buyer buyer) {
        buyers.remove(buyer);
    }
    public void removeUser(Seller seller) {
        sellers.remove(seller);
    }
}
