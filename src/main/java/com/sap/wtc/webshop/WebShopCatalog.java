package com.sap.wtc.webshop;

import java.util.ArrayList;
import java.util.Iterator;

public class WebShopCatalog {
    private ArrayList<WebShopItem> catalogItems = new ArrayList<>();
    
    public WebShopCatalog() {
        generateLocalTestData();
    }
    
    public ArrayList<WebShopItem> getCatalog() {
        return catalogItems;
    }
    
    public WebShopItem getItem(int itemID) {
        Iterator<WebShopItem> iterator = catalogItems.iterator();
        while (iterator.hasNext()) {
            WebShopItem catalogueItem = iterator.next();
            if (catalogueItem.getId() == itemID) {
                return catalogueItem;
            }
        }
        return null;
    }
    
    private void generateLocalTestData() {
        catalogItems.add(new WebShopItem(111, "Refactoring", 59.99, "Books"));
        catalogItems.add(new WebShopItem(137, "Iron", 49.65, "Household"));
        catalogItems.add(new WebShopItem(250, "Narina", 12.99, "DVDs"));
        catalogItems.add(new WebShopItem(270, "Moby Dick", 15.66, "Books"));
        catalogItems.add(new WebShopItem(362, "Domain Driven Design", 39.99, "Books"));
        catalogItems.add(new WebShopItem(365, "Watehose", 15.66, "Gardening"));
        catalogItems.add(new WebShopItem(368, "Courageous", 10.99, "DVDs"));
        catalogItems.add(new WebShopItem(458, "Screen", 215.55, "Computers"));
        catalogItems.add(new WebShopItem(570, "Superman", 16.99, "DVDs"));
        catalogItems.add(new WebShopItem(964, "Mouse", 5.66, "Computers"));
    }
}
