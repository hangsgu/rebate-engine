package com.sap.wtc.webshop;

import java.math.BigDecimal;

public interface WebShop {
    boolean buyCart();
    
    void addItemToCart(int itemID, int quantity);
    
    void removeItemFromCart(int itemId, int quantity);
    
    WebShoppingCart getCart();
    
    WebShopCatalog getCatalogue();
    
    BigDecimal getCartRebate();
    
    String getCartRebateReason();
    
    BigDecimal getItemTotalWithItemRebate();
    
    BigDecimal getFinalAmount();
}