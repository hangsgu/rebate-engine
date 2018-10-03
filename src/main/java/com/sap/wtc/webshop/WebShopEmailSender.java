package com.sap.wtc.webshop;

public interface WebShopEmailSender {
    void send(String subject, String messageText);
}
