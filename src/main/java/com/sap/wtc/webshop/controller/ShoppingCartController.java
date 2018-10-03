package com.sap.wtc.webshop.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.wtc.webshop.WebShop;
import com.sap.wtc.webshop.impl.WebShopImpl;
import com.sap.wtc.webshop.model.WebShopCartModel;
import com.sap.wtc.webshop.model.WebShopCartTO;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(path = ShoppingCartController.ROOT_PATH)
@RequestScope
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ShoppingCartController {
    static final String ROOT_PATH = "/api/v1/cart";
    private WebShop shop = new WebShopImpl();
    
    /**
     * @RequestBody is bound to the method argument. HttpMessageConverter
     * resolves method argument depending on the content type.
     */
    @PostMapping
    public ResponseEntity<WebShopCartTO> add(@RequestBody WebShoppingCartAddItemsRequest itemsToAdd,
                                             UriComponentsBuilder uriComponentsBuilder) throws URISyntaxException {
        
        for (ShoppingCartController.WebShoppingCartAddedItem item : itemsToAdd.addedItems) {
            shop.addItemToCart(item.itemID, (item.quantity > 0) ? item.quantity : 1);
        }
        UriComponents uriComponents = uriComponentsBuilder.path(ROOT_PATH).buildAndExpand();
        
        return ResponseEntity.created(new URI(uriComponents.getPath()))
                             .body(new WebShopCartModel().getWebCartItems(shop));
    }
    
    @PostMapping("/items/{itemToAdd}")
    public ResponseEntity<WebShopCartTO> add(@PathVariable int itemToAdd, @RequestParam int quantityToAdd,
                                             UriComponentsBuilder uriComponentsBuilder) throws URISyntaxException {
        
        shop.addItemToCart(itemToAdd, (quantityToAdd > 0) ? quantityToAdd : 1);
        
        UriComponents uriComponents = uriComponentsBuilder.path(ROOT_PATH + "/items/" + itemToAdd).buildAndExpand();
        
        return ResponseEntity.created(new URI(uriComponents.getPath()))
                             .body(new WebShopCartModel().getWebCartItems(shop));
    }
    
    @GetMapping
    public WebShopCartTO getShoppingCart() {
        return new WebShopCartModel().getWebCartItems(shop);
    }
    
    @GetMapping("/items")
    public WebShopCartTO getShoppingCartItems() {
        return new WebShopCartModel().getWebCartItems(shop);
    }
    
    @DeleteMapping("/items/{itemToDelete}")
    public WebShopCartTO deleteItemFromTheCart(@PathVariable int itemToDelete, @RequestParam int quantityToRemove) {
        
        shop.removeItemFromCart(itemToDelete, quantityToRemove);
        return new WebShopCartModel().getWebCartItems(shop);
    }
    
    @PostMapping("/checkout")
    public WebShopCartTO checkout() {
        WebShopCartTO cartTO = new WebShopCartModel().getWebCartItems(shop);
        cartTO.setCheckoutResult(shop.buyCart());
        return cartTO;
    }
    
    public static class WebShoppingCartAddItemsRequest {
        @JsonProperty("items")
        public List<WebShoppingCartAddedItem> addedItems = new ArrayList<>();
    }
    
    public static class WebShoppingCartAddedItem {
        @JsonProperty("itemID")
        public int itemID;
        
        @JsonProperty("quantity")
        public int quantity;
    }
}
