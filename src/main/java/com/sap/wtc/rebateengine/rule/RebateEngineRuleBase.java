package com.sap.wtc.rebateengine.rule;

import com.sap.wtc.webshop.WebShoppingCart;
import com.sap.wtc.webshop.WebShoppingCartItem;

public abstract class RebateEngineRuleBase
{
    private int requiredItemID;
    private int requiredQuantity;

    RebateEngineRuleBase(int requiredItemID, int requiredQuantity)
    {
        this.requiredItemID = requiredItemID;
        this.requiredQuantity = requiredQuantity;
    }

    public void apply(WebShoppingCart cart)
    {
        cart.getCartItems()
                .stream()
                .filter(this::isCartItemEligibleForRule)
                .forEach(this::applyRebateForEachSetOfItemsEqualToRequiredQuantity);
    }

    private boolean isCartItemEligibleForRule(WebShoppingCartItem cartItem)
    {
        return cartItem.getId() == requiredItemID && cartItem.getQuantity() >= requiredQuantity;
    }

    private void applyRebateForEachSetOfItemsEqualToRequiredQuantity (WebShoppingCartItem cartItem)
    {
        for (int i = 1; i <= cartItem.getQuantity() / requiredQuantity; i++) applyRuleToCartItem(cartItem);
    }

    public abstract void applyRuleToCartItem(WebShoppingCartItem cartItem);

}
