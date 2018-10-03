package com.sap.wtc.webshop.impl;

import com.sap.wtc.webshop.WebShopEmailSender;
import java.math.BigDecimal;

public class WebShopPaymentImpl {
    
    public boolean pay(WebShopEmailSender emailSender, BigDecimal amountToPay) {
        WebShopProcessPaymentImpl processPayment = new WebShopProcessPaymentImpl();
        boolean processResultSuccess = processPayment.process("test", amountToPay);
        if (processResultSuccess) {
            emailSender.send("Payment processed for user test", "Value " + amountToPay);
        } else {
            emailSender.send("Payment failed for user test", "Value " + amountToPay);
        }
        return processResultSuccess;
    }
}
