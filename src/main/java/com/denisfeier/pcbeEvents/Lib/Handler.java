package com.denisfeier.pcbeEvents.Lib;

public class Handler implements Channel<Event> {
    @Override
    public void dispatch(Event message) {
        System.out.println(message.getClass());
    }
}
