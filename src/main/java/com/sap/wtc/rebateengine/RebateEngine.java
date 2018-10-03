package com.sap.wtc.rebateengine;

import com.sap.wtc.rebateengine.rule.RebateEngineRuleBase;
import com.sap.wtc.webshop.WebShoppingCart;
import com.sap.wtc.webshop.WebShoppingCartItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RebateEngine {
    private List<RebateEngineRuleBase> rules = new ArrayList<>();
    
    public RebateEngine(List<RebateEngineRuleBase> rules) {
        if (rules != null)
            this.rules = rules;
    }
    
    public BigDecimal computeRebate(WebShoppingCart cart) {
        for (RebateEngineRuleBase rebateRule : rules)
            rebateRule.apply(cart);
        
        return getTotalRebateAmount(cart);
    }
    
    private BigDecimal getTotalRebateAmount(WebShoppingCart cart) {
        BigDecimal totalRebateAmount = cart.getCartItems().stream()
                .map(WebShoppingCartItem::getRebateAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        cart.setCartRebateAmount(totalRebateAmount);
        return totalRebateAmount;
    }
}