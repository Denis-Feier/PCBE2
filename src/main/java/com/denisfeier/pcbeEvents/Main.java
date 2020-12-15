package com.denisfeier.pcbeEvents;

import com.denisfeier.pcbeEvents.Entity.*;
import com.denisfeier.pcbeEvents.TheMarket.Market;
import com.denisfeier.pcbeEvents.TheMarket.MarketSingleton;
import com.denisfeier.pcbeEvents.events.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        Market market = MarketSingleton.getInstance();
        System.out.println("Starting market ..");
        initApp(2,2,market);

        System.out.println("Ending market ..");
    }

    private static void initApp(int numberOfSellers, int numberOfBuyers, Market market) throws FileNotFoundException {
        final Random rnd = new Random();

        Scanner sc = null;
        sc = new Scanner(new File("D:/GIT/meh/PCBE/src/main/java/com/denisfeier/constants/stocks.txt"));
        List<String> lines0 = new ArrayList<String>();
        List<String> StockNames = new ArrayList<String>();
        while (sc.hasNextLine()) {
            lines0.add(sc.nextLine());
            String[] parts = sc.nextLine().split("-");
            StockNames.add(parts[0]);
        }
        String[] StockNameArray = StockNames.toArray(new String[0]);
        sc = new Scanner(new File("D:/GIT/meh/PCBE/src/main/java/com/denisfeier/constants/names.txt"));
        List<String> Names = new ArrayList<String>();
        while (sc.hasNextLine()) {
            Names.add(sc.nextLine());
        }
        String[] NameArray = Names.toArray(new String[0]);

        List<Seller> sellers = new ArrayList<Seller>();
        List<Buyer> buyers = new ArrayList<Buyer>();

        Buyer buyer = new Buyer("CRISTINA",99999,new ArrayList<>(),market);
        Seller seller = new Seller("ALEXTEST",99999,new ArrayList<>(),market);
        sellers.add(new Seller("ALEXTEST",99999,new ArrayList<>(),market));
        buyers.add(new Buyer("CRISTINA",99999,new ArrayList<>(),market));
        seller.subscribeToEvent(Event.Type.NEW_DEMAND,Person.Filter.NONE,-1);
        seller.subscribeToEvent(Event.Type.MATCHED_SUPPLY_DEMAND,Person.Filter.NONE,-1);
        buyer.subscribeToEvent(Event.Type.NEW_SUPPLY,Person.Filter.NONE,-1);
        buyer.subscribeToEvent(Event.Type.MATCHED_SUPPLY_DEMAND,Person.Filter.NONE,-1);

        for (int i = 0; i < numberOfSellers; i++) {
            int randomName = rnd.nextInt(18200);
            Seller s = new Seller(NameArray[randomName],rnd.nextDouble()*1000,new ArrayList<>(),market);
            sellers.add(s);
            market.addUser(s);
        }

        for (int i = 0; i < numberOfBuyers; i++) {
            int randomName = rnd.nextInt(18200);
            Buyer b = new Buyer(NameArray[randomName],rnd.nextDouble()*1000,new ArrayList<>(),market);
            buyers.add(b);
            market.addUser(b);
        }

        for(final Seller s : sellers){
            s.subscribeToEvent(Event.Type.NEW_SUPPLY, Person.Filter.NONE,-1);
            s.subscribeToEvent(Event.Type.MATCHED_SUPPLY_DEMAND,Person.Filter.NONE,-1);
            s.subscribeToEvent(Event.Type.NEW_BUYER,Person.Filter.NONE,-1);
            s.subscribeToEvent(Event.Type.NEW_UPDATED_DEMAND,Person.Filter.NONE,-1);
            s.subscribeToEvent(Event.Type.NEW_UPDATED_SUPPLY,Person.Filter.NONE,-1);
        }

        for(final Buyer b : buyers){
            b.subscribeToEvent(Event.Type.NEW_DEMAND,Person.Filter.NONE,-1);
            b.subscribeToEvent(Event.Type.MATCHED_SUPPLY_DEMAND,Person.Filter.NONE,-1);
            b.subscribeToEvent(Event.Type.NEW_UPDATED_DEMAND,Person.Filter.NONE,-1);
            b.subscribeToEvent(Event.Type.NEW_UPDATED_SUPPLY,Person.Filter.NONE,-1);
        }

        for (final Seller s : sellers) {
            int randomStock = rnd.nextInt(1820);
            s.addSupplyToMarket(new Supply(StockNameArray[randomStock],rnd.nextInt(10) + 1, rnd.nextInt(10) + 1,s));
        }

        for (final Buyer b : buyers) {
            int randomStock = rnd.nextInt(1820);
            b.addDemandToMarket(new Demand(StockNameArray[randomStock],rnd.nextInt(10) + 1, rnd.nextInt(10) + 1, b));
        }
        seller.addSupplyToMarket(new Supply("OurSupply",4000, 900,seller));
        buyer.addDemandToMarket(new Demand("OurSupply",1000, 1000,buyer));
        seller.addSupplyToMarket(new Supply("FirstOurs",4004,400,seller));

        System.out.println(market.getSupply("OurSupply").getPrice());
        Seller theOne = new Seller("Mr Gary Lary",99099,new ArrayList<>(),market);
        theOne.updateSupplyPrice(market.getSupply("OurSupply"),404040.0);
        Supply modifiedSupply = market.getSupply("OurSupply");
        System.out.println(modifiedSupply.getName() + "  " + modifiedSupply.getPrice());
        theOne.subscribeToEvent(Event.Type.NEW_BUYER, Person.Filter.NONE,-1);

        theOne.subscribeToEvent(Event.Type.NEW_SELLER, Person.Filter.NONE,-1);


        market.addUser(new Buyer("JOHNY TEST",rnd.nextDouble()*1000,new ArrayList<>(),market));
        market.addUser(new Buyer("JOHNY TEST1",rnd.nextDouble()*1000,new ArrayList<>(),market));
        market.addUser(new Seller("JOHNY TEST2",rnd.nextDouble()*1000,new ArrayList<>(),market));
        market.addUser(new Buyer("JOHNY TEST3",rnd.nextDouble()*1000,new ArrayList<>(),market));

        theOne.updateSupplyPrice(market.getSupply("FirstOurs"),1000.0);
        theOne.updateSupplyPrice(market.getSupply("FirstOurs"),2000.0);
    }
}
