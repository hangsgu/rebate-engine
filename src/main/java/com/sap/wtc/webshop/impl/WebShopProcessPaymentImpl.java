package com.sap.wtc.webshop.impl;

import java.math.BigDecimal;

public class WebShopProcessPaymentImpl {
    public boolean process(String userName, BigDecimal value) {
        // A basic implementation ; no concrete business functionality
        return (userName != null) && (value.compareTo(BigDecimal.ZERO) > 0);
    }
}
