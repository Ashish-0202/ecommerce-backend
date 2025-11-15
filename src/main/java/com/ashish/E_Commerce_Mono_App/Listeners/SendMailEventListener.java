package com.ashish.E_Commerce_Mono_App.Listeners;

import com.ashish.E_Commerce_Mono_App.Event.SendMailEvent;
import com.ashish.E_Commerce_Mono_App.Service.StatusUpdateNotificationService;
import jakarta.mail.MessagingException;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SendMailEventListener {

    private final StatusUpdateNotificationService statusUpdateNotificationService;

    public SendMailEventListener(StatusUpdateNotificationService statusUpdateNotificationService) {
        this.statusUpdateNotificationService = statusUpdateNotificationService;
    }

    @EventListener
    public void handleSendMailEvent(SendMailEvent sendMailEvent) throws MessagingException {
        statusUpdateNotificationService.sendStatusUpdateNotification(sendMailEvent.getOrder());
    }
}
