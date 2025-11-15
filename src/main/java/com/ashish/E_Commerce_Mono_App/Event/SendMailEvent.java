package com.ashish.E_Commerce_Mono_App.Event;

import com.ashish.E_Commerce_Mono_App.Entity.orders;
import lombok.Getter;

@Getter
public class SendMailEvent {

    private final orders order;
    public SendMailEvent(orders order) {
        this.order = order;
    }

}
