package com.sap.wtc.rebateengine;

import com.sap.wtc.webshop.WebShoppingCart;
import com.sap.wtc.webshop.WebShoppingCartItem;

import java.util.List;

public class WebShoppingCartFactory
{
    public static WebShoppingCart getShoppingCart(List<WebShoppingCartItem> items)
    {
        WebShoppingCart cart = new WebShoppingCart();
        cart.setCartItems(items);
        return cart;
    }

    public static WebShoppingCart getShoppingCart()
    {
        return new WebShoppingCart();
    }
}
