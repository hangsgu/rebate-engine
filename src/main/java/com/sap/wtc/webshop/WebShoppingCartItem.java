package com.sap.wtc.webshop;

import java.math.BigDecimal;

public class WebShoppingCartItem {
    private int id;
    private String category;
    private String name;
    private BigDecimal standardPrice = BigDecimal.ZERO;
    private int quantity;
    private BigDecimal rebateAmount = BigDecimal.ZERO;
    private String rebateReason;
    
    public WebShoppingCartItem(int id, int quantity, BigDecimal standardPrice) {
        this.id = id;
        this.quantity = quantity;
        this.standardPrice = standardPrice;
    }
    
    public WebShoppingCartItem(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }
    
    public WebShoppingCartItem(int id, int quantity, BigDecimal standardPrice, String category) {
        this.id = id;
        this.quantity = quantity;
        this.standardPrice = standardPrice;
        this.category = category;
    }
    
    public WebShoppingCartItem(String name, int quantity, BigDecimal standardPrice, String category) {
        this.name = name;
        this.quantity = quantity;
        this.standardPrice = standardPrice;
        this.category = category;
    }
    
    public WebShoppingCartItem(int id, String name, int quantity, BigDecimal standardPrice, String category) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.standardPrice = standardPrice;
        this.category = category;
    }
    
    public BigDecimal getRebateAmount() {
        return rebateAmount;
    }
    
    public void setRebateAmount(BigDecimal rebateAmount) {
        this.rebateAmount = rebateAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    
    public String getRebateReason() {
        return rebateReason;
    }
    
    public void setRebateReason(String rebateReason) {
        this.rebateReason = rebateReason;
    }
    
    public int getId() {
        return id;
    }
    
    public String getCategory() {
        return category;
    }
    
    public String getName() {
        return name;
    }
    
    public BigDecimal getStandardPrice() {
        return standardPrice;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public BigDecimal getStandardTotal() {
        return standardPrice.multiply(new BigDecimal(quantity));
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
