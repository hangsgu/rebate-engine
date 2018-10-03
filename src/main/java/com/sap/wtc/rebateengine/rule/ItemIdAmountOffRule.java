package com.sap.wtc.rebateengine.rule;

import com.sap.wtc.webshop.WebShopConfigError;
import com.sap.wtc.webshop.WebShoppingCart;
import com.sap.wtc.webshop.WebShoppingCartItem;

import java.math.BigDecimal;

public class ItemIdAmountOffRule extends RebateEngineRuleBase
{

    private BigDecimal amountOff;

    public ItemIdAmountOffRule(int requiredItemID, int requiredQuantity, BigDecimal amountOff)
    {
        super(requiredItemID, requiredQuantity);
        setAmountOff(amountOff);
    }

    @Override
    public void applyRuleToCartItem(WebShoppingCartItem cartItem)
    {
        cartItem.setRebateAmount(cartItem.getRebateAmount().add(amountOff));
    }

    public void setAmountOff(BigDecimal amountOff)
    {
        if (amountOff.compareTo(BigDecimal.ZERO) < 1)
            throw new WebShopConfigError();
        this.amountOff = amountOff;
    }
}
