package com.denisfeier.pcbeEvents.Events;

import com.denisfeier.pcbeEvents.Entity.Buyer;
import com.denisfeier.pcbeEvents.Lib.Event;
import com.denisfeier.pcbeEvents.market.MarketState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class BuyerFoundEvent extends Event {
    private MarketState marketState;
    private Buyer buyer;
}
