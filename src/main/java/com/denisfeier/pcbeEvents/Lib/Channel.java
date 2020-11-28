package com.denisfeier.pcbeEvents.Lib;

public interface Channel<E extends Message> {

    public void dispatch(E message);

}
