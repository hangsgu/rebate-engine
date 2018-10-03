package com.sap.wtc.webshop.impl;

import com.sap.wtc.webshop.WebShop;
import com.sap.wtc.webshop.WebShopCatalog;
import com.sap.wtc.webshop.WebShopItem;
import com.sap.wtc.webshop.WebShoppingCart;
import java.math.BigDecimal;

public class WebShopImpl implements WebShop {
    private WebShoppingCart cart;
    private WebShopCatalog catalogue;
    
    public WebShopImpl() {
        catalogue = new WebShopCatalog();
        buildNewEmptyCart();
    }
    
    private void buildNewEmptyCart() {
        cart = new WebShoppingCart();
    }
    
    private void calculateRebates() {
        // TODO: You should call the rebate engine here
    }
    
    @Override
    public boolean buyCart() {
        WebShopPaymentImpl payment = new WebShopPaymentImpl();
        boolean paymentResult = payment.pay(new WebShopEmailSenderImpl(), getFinalAmount());
        if (paymentResult) {
            buildNewEmptyCart();
        }
        return paymentResult;
    }
    
    @Override
    public void addItemToCart(int itemID, int quantity) {
        WebShopItem itemTobeAdded = catalogue.getItem(itemID);
        cart.addItemToCart(itemTobeAdded, quantity);
        calculateRebates();
    }
    
    @Override
    public void removeItemFromCart(int itemID, int quantity) {
        cart.removeItem(itemID, quantity);
        calculateRebates();
    }
    
    @Override
    public WebShoppingCart getCart() {
        return cart;
    }
    
    @Override
    public WebShopCatalog getCatalogue() {
        return catalogue;
    }
    
    @Override
    public BigDecimal getCartRebate() {
        return cart.getCartRebateAmount();
    }
    
    @Override
    public String getCartRebateReason() {
        return cart.getCartRebateReason();
    }
    
    @Override
    public BigDecimal getItemTotalWithItemRebate() {
        return cart.getStandardTotal().subtract(cart.getTotalItemRebate());
    }
    
    @Override
    public BigDecimal getFinalAmount() {
        return cart.getStandardTotal().subtract(cart.getTotalItemRebate()).subtract(cart.getCartRebateAmount());
    }
}
