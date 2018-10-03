package com.sap.wtc.webshop.model;

import com.sap.wtc.webshop.WebShoppingCartItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class WebShopCartTO {
    
    private List<WebShoppingCartItem> cartItems = new ArrayList<>();
    private BigDecimal itemTotal;
    private boolean checkoutResult;
    private BigDecimal finalAmount;
    private BigDecimal cartRebateAmount;
    private String cartRebateReason;
    
    public List<WebShoppingCartItem> getCartItems() {
        return cartItems;
    }
    
    public void setCartItems(List<WebShoppingCartItem> cartItems) {
        this.cartItems = cartItems;
    }
    
    public boolean isCheckoutResult() {
        return checkoutResult;
    }
    
    public void setCheckoutResult(boolean checkoutResult) {
        this.checkoutResult = checkoutResult;
    }
    
    public BigDecimal getCartRebateAmount() {
        return cartRebateAmount;
    }
    
    public void setCartRebateAmount(BigDecimal cartRebateAmount) {
        this.cartRebateAmount = cartRebateAmount;
    }
    
    public String getCartRebateReason() {
        return cartRebateReason;
    }
    
    public void setCartRebateReason(String cartRebateReason) {
        this.cartRebateReason = cartRebateReason;
    }
    
    public BigDecimal getItemTotal() {
        return itemTotal;
    }
    
    public void setItemTotal(BigDecimal itemTotal) {
        this.itemTotal = itemTotal;
    }
    
    public BigDecimal getFinalAmount() {
        return finalAmount;
    }
    
    public void setFinalAmount(BigDecimal finalAmount) {
        this.finalAmount = finalAmount;
    }
}
