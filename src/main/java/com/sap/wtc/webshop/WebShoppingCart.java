package com.sap.wtc.webshop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WebShoppingCart {
    
    static final BigDecimal ZERO = BigDecimal.ZERO;
    
    private List<WebShoppingCartItem> cartItems = new ArrayList<>();
    private BigDecimal cartRebateAmount = BigDecimal.ZERO;
    private String cartRebateReason;
    
    private BigDecimal standardTotal = BigDecimal.ZERO;
    
    public WebShoppingCart() {
    
    }
    
    private void calculateStandardTotal() {
        Iterator<WebShoppingCartItem> cartItemsIterator = cartItems.iterator();
        this.standardTotal = ZERO;
        while (cartItemsIterator.hasNext()) {
            WebShoppingCartItem itemInTheCart = cartItemsIterator.next();
            this.standardTotal = standardTotal.add(itemInTheCart.getStandardTotal());
        }
    }
    
    public List<WebShoppingCartItem> getCartItems() {
        return cartItems;
    }
    
    public void setCartItems(List<WebShoppingCartItem> cartItems) {
        this.cartItems = cartItems;
    }
    
    public void addItemToCart(WebShopItem shopItem) {
        WebShoppingCartItem existingItemInTheCart = getShoppingCartItem(shopItem.getId());
        if (existingItemInTheCart == null) {
            cartItems.add(createNewShoppingCartItem(shopItem));
        } else {
            existingItemInTheCart.setQuantity(existingItemInTheCart.getQuantity() + 1);
        }
        
        calculateStandardTotal();
    }
    
    public void addItemToCart(WebShopItem shopItem, int quantity) {
        WebShoppingCartItem existingItemInTheCart = getShoppingCartItem(shopItem.getId());
        if (existingItemInTheCart == null) {
            cartItems.add(createNewShoppingCartItem(shopItem, quantity));
        } else {
            existingItemInTheCart.setQuantity(existingItemInTheCart.getQuantity() + quantity);
        }
        calculateStandardTotal();
    }
    
    private WebShoppingCartItem getShoppingCartItem(int id) {
        for (WebShoppingCartItem itemInTheCart : cartItems) {
            if (itemInTheCart != null && itemInTheCart.getId() == id) {
                return itemInTheCart;
            }
        }
        return null;
    }
    
    private WebShoppingCartItem createNewShoppingCartItem(WebShopItem shopItem) {
        return new WebShoppingCartItem(shopItem.getId(), shopItem.getName(), 1, BigDecimal.valueOf(shopItem.getPrice()),
                                       shopItem.getCategory());
    }
    
    private WebShoppingCartItem createNewShoppingCartItem(WebShopItem shopItem, int quantity) {
        return new WebShoppingCartItem(shopItem.getId(), shopItem.getName(), quantity,
                                       BigDecimal.valueOf(shopItem.getPrice()), shopItem.getCategory());
    }
    
    public void removeItem(int itemID) {
        WebShoppingCartItem cartItem = getShoppingCartItem(itemID);
        if (cartItem != null) {
            if (cartItem.getQuantity() == 1)
                cartItems.remove(cartItem);
            else
                cartItem.setQuantity(cartItem.getQuantity() - 1);
        }
        calculateStandardTotal();
    }
    
    public void removeItem(int itemID, int quantityToRemove) {
        WebShoppingCartItem cartItem = getShoppingCartItem(itemID);
        if (cartItem != null) {
            if (cartItem.getQuantity() == 1)
                cartItems.remove(cartItem);
            else
                cartItem.setQuantity(cartItem.getQuantity() - quantityToRemove);
        }
        calculateStandardTotal();
    }
    
    public int getNumberOfItemsInCart() {
        return cartItems.size();
    }
    
    public BigDecimal getStandardTotal() {
        return standardTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
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
    
    public BigDecimal getTotalItemRebate() {
        // TODO: return accumulation of Item level Rebate Amount.
        return BigDecimal.ZERO;
    }
}
