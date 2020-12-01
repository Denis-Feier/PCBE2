package com.denisfeier.pcbeEvents.market;

import com.denisfeier.pcbeEvents.Entity.Buyer;
import com.denisfeier.pcbeEvents.Entity.Seller;
import com.denisfeier.pcbeEvents.Entity.Stock;
import com.denisfeier.pcbeEvents.Entity.StockElement;
import com.denisfeier.pcbeEvents.Lib.EventDispatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MarketState {
    private EventDispatcher dispatcher;
    private List<Buyer> buyers;
    private List<Seller> sellers;

    private List<Stock> stocks;

    public MarketState(EventDispatcher dispatcher) {
        this.dispatcher = dispatcher;
        this.sellers = new ArrayList<>();
        this.buyers = new ArrayList<>();
    }

    public void addBuyer(Buyer buyer) {
        this.buyers.add(buyer);
    }

    public void addSeller(Seller seller) {
        this.sellers.add(seller);
    }

    public Stock updateStock(String id, double newPrice) {

        List<Stock> element = this.stocks
                .stream()
                .filter(stockElement -> stockElement.getId().equals(id))
                .collect(Collectors.toList());

        if (element.size() != 0) {
            Stock stockElement = element.get(0);
            stockElement.setPrice(newPrice);
            return stockElement;
        } else {
            return null;
        }
    }
}
