package com.denisfeier.pcbeEvents.Lib;

public interface Message {
    public Class<? extends Message> getType();
}