package com.denisfeier.pcbeEvents.Events;

import com.denisfeier.pcbeEvents.Entity.Supply;
import com.denisfeier.pcbeEvents.Lib.Event;
import com.denisfeier.pcbeEvents.market.MarketState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class NewStockEvent extends Event {
    private MarketState marketState;
    private Supply supply;
}
