package com.sap.wtc.rebateengine;

import com.sap.wtc.rebateengine.rule.ItemIdAmountOffRule;
import com.sap.wtc.rebateengine.rule.RebateEngineRuleBase;
import com.sap.wtc.webshop.WebShoppingCart;
import com.sap.wtc.webshop.WebShoppingCartItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class RebateEngineTest
{
    private static RebateEngine rebateEngine;

    private WebShoppingCart shoppingCart;

    @BeforeClass
    public static void setup()
    {
        List<RebateEngineRuleBase> rules = new ArrayList<RebateEngineRuleBase>();
        rules.add(new ItemIdAmountOffRule(1, 2, new BigDecimal(5)));
        rules.add(new ItemIdAmountOffRule(2, 3, new BigDecimal(7)));
        rules.add(new ItemIdAmountOffRule(3, 1, new BigDecimal(500)));
        rebateEngine = new RebateEngine(rules);
    }

    @Test
    public void noRebateForEmptyCart()
    {
        shoppingCart = WebShoppingCartFactory.getShoppingCart();
        rebateEngine.computeRebate(shoppingCart);

        assertEquals(BigDecimal.ZERO, rebateEngine.computeRebate(shoppingCart));
    }

    @Test
    public void fiveTotalRebateForCart()
    {
        shoppingCart = WebShoppingCartFactory.getShoppingCart(
                Arrays.asList(new WebShoppingCartItem(1, 3, BigDecimal.valueOf(15)),
                        new WebShoppingCartItem(2, 1, BigDecimal.valueOf(10))));

        assertEquals(BigDecimal.valueOf(5).setScale(2, BigDecimal.ROUND_HALF_UP), rebateEngine.computeRebate(shoppingCart));
    }

    @Test
    public void twelveTotalRebateForCart()
    {
        shoppingCart = WebShoppingCartFactory.getShoppingCart(
                Arrays.asList(new WebShoppingCartItem(1, 2, BigDecimal.valueOf(15)),
                        new WebShoppingCartItem(2, 3, BigDecimal.valueOf(10))));

        assertEquals(BigDecimal.valueOf(12).setScale(2, BigDecimal.ROUND_HALF_UP), rebateEngine.computeRebate(shoppingCart));
    }

    @Test
    public void fiveHundredAndTwelveTotalRebateForCart()
    {
        shoppingCart = WebShoppingCartFactory.getShoppingCart(
                Arrays.asList(new WebShoppingCartItem(1, 2, BigDecimal.valueOf(15)),
                        new WebShoppingCartItem(2, 3, BigDecimal.valueOf(10)),
                        new WebShoppingCartItem(3, 1, BigDecimal.valueOf(12))));

        assertEquals(BigDecimal.valueOf(512).setScale(2, BigDecimal.ROUND_HALF_UP), rebateEngine.computeRebate(shoppingCart));
    }
}