package com.ashish.E_Commerce_Mono_App.Service;

import com.ashish.E_Commerce_Mono_App.Config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private final TwilioConfig config;

    public SmsService(TwilioConfig config) {
        this.config = config;
        Twilio.init(config.getAccountSid(),config.getAuthToken());
    }

    public String sendSms(String to,String body){
        Message message= Message.creator(new PhoneNumber(to),
                new PhoneNumber(config.getFromNumber()), body)
                .create();

        return message.getSid();
    }
}
