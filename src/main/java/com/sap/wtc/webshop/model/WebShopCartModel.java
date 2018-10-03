package com.sap.wtc.webshop.model;

import com.sap.wtc.webshop.WebShop;
import com.sap.wtc.webshop.WebShoppingCart;

public class WebShopCartModel {
    
    public WebShopCartTO getWebCartItems(WebShop shop) {
        
        WebShoppingCart cart = shop.getCart();
        WebShopCartTO cartTO = new WebShopCartTO();
        cartTO.setCartItems(cart.getCartItems());
        
        cartTO.setItemTotal(shop.getItemTotalWithItemRebate());
        cartTO.setFinalAmount(shop.getFinalAmount());
        cartTO.setCartRebateAmount(shop.getCartRebate());
        cartTO.setCartRebateReason(shop.getCartRebateReason());
        return cartTO;
    }
}
