package com.sap.wtc.rebateengine.rule;

import com.sap.wtc.rebateengine.WebShoppingCartFactory;
import com.sap.wtc.webshop.WebShopConfigError;
import com.sap.wtc.webshop.WebShoppingCart;
import com.sap.wtc.webshop.WebShoppingCartItem;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing RebateEngineRuleForItemID class rule that gets triggered on ItemID
 * and required quantity
 */

public class RebateEngineRuleForItemIDTest
{
    private static RebateEngineRuleBase itemIDRule;

    private static RebateEngineRuleBase itemIDPercentageRule;

    private WebShoppingCart shoppingCart;

    @BeforeClass
    public static void setup()
    {
        itemIDRule = new ItemIdAmountOffRule(1, 2, new BigDecimal(5));
        itemIDPercentageRule = new ItemIdPercentageOffRule(2, 2, new BigDecimal(50));
    }

    @Test
    public void isRebateEngineRuleForItemIDAppliedToItem()
    {
        shoppingCart = WebShoppingCartFactory.getShoppingCart(
                Arrays.asList(new WebShoppingCartItem(1, 2, BigDecimal.valueOf(15))));
        itemIDRule.apply(shoppingCart);

        assertEquals(BigDecimal.valueOf(5).setScale(2, BigDecimal.ROUND_HALF_UP),
                shoppingCart.getCartItems().get(0).getRebateAmount());
    }

    @Test
    public void noRebateForNonRequiredId()
    {
        shoppingCart = WebShoppingCartFactory.getShoppingCart(
                Arrays.asList(new WebShoppingCartItem(2, 2, BigDecimal.valueOf(15))));
        itemIDRule.apply(shoppingCart);

        assertEquals(BigDecimal.ZERO, shoppingCart.getCartItems().get(0).getRebateAmount());
    }

    @Test
    public void noRebateForNonRequiredQuantity()
    {
        shoppingCart = WebShoppingCartFactory.getShoppingCart(
                Arrays.asList(new WebShoppingCartItem(1, 1, BigDecimal.valueOf(15))));
        itemIDRule.apply(shoppingCart);

        assertEquals(BigDecimal.ZERO, shoppingCart.getCartItems().get(0).getRebateAmount());
    }

    @Test
    public void isPercentageRuleAppliedToItem()
    {
        shoppingCart = WebShoppingCartFactory.getShoppingCart(
                Arrays.asList(new WebShoppingCartItem(2, 4, BigDecimal.valueOf(100)),
                        new WebShoppingCartItem(2, 1, BigDecimal.valueOf(100))));
        itemIDPercentageRule.apply(shoppingCart);

        assertEquals(BigDecimal.valueOf(100).setScale(2, BigDecimal.ROUND_HALF_UP),
                shoppingCart.getCartItems().get(0).getRebateAmount());
    }

    @Test(expected = WebShopConfigError.class)
    public void shouldThrowWebConfigErrorExceptionWhenAmountOffIsZero()
    {
        itemIDRule = new ItemIdAmountOffRule(1, 2, BigDecimal.ZERO);
    }

    @Test(expected = WebShopConfigError.class)
    public void shouldThrowWebConfigErrorExceptionWhenAmountOffIsNegative()
    {
        itemIDRule = new ItemIdAmountOffRule(1, 2, new BigDecimal(-732));

    }

    @Test(expected = WebShopConfigError.class)
    public void shouldThrowWebConfigErrorExceptionWhenPercentageOffIsZero()
    {
        itemIDRule = new ItemIdPercentageOffRule(1, 2, new BigDecimal(0));
    }

    @Test(expected = WebShopConfigError.class)
    public void shouldThrowWebConfigErrorExceptionWhenPercentageOffIsNegative()
    {
        itemIDRule = new ItemIdPercentageOffRule(1, 2, new BigDecimal(-1323));
    }
}
