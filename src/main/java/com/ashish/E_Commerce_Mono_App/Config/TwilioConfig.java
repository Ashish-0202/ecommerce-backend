package com.ashish.E_Commerce_Mono_App.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {

    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    @Value("${twilio.phone-number}")
    private String fromNumber;

    public String getAccountSid() { return accountSid; }
    public String getAuthToken() { return authToken; }
    public String getFromNumber() { return fromNumber; }
}
