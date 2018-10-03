package com.sap.wtc.rebateengine.rule;

import com.sap.wtc.webshop.WebShopConfigError;
import com.sap.wtc.webshop.WebShoppingCartItem;

import java.math.BigDecimal;

public class ItemIdPercentageOffRule extends RebateEngineRuleBase
{
    private BigDecimal percentageOff;

    public ItemIdPercentageOffRule(int requiredItemID, int requiredQuantity, BigDecimal percentageOff)
    {
        super(requiredItemID, requiredQuantity);
        setPercentageOff(percentageOff);
    }

    @Override
    public void applyRuleToCartItem(WebShoppingCartItem cartItem)
    {
            cartItem.setRebateAmount(cartItem.getRebateAmount().add( percentageOff));
    }

    public void setPercentageOff(BigDecimal percentageOff)
    {
        if (percentageOff.compareTo(BigDecimal.ZERO) < 1)
            throw new WebShopConfigError();
        this.percentageOff = percentageOff;
    }
}
