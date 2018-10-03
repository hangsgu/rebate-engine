package com.sap.wtc.webshop.controller;

import com.sap.wtc.webshop.WebShopCatalog;
import com.sap.wtc.webshop.WebShopItem;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(path = CatalogController.PATH)
@Scope(WebApplicationContext.SCOPE_SESSION)
public class CatalogController {
    public static final String PATH = "/api/v1/catalogItems";
    private static final Map<Long, WebShopItem> catalogItems = new HashMap<>();
    private WebShopCatalog catalog;
    
    public CatalogController() {
        catalog = new WebShopCatalog();
    }
    
    @GetMapping
    public ResponseEntity<Iterable<WebShopItem>> catalogItems() {
        return new ResponseEntity<>(catalog.getCatalog(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    // We do not use primitive "long" type here to avoid unnecessary autoboxing
    public WebShopItem catalogItemById(@PathVariable("id") Long id) {
        if (!catalogItems.containsKey(id)) {
            throw new NotFoundException(id + " not found");
        }
        
        return catalogItems.get(id);
    }
    
    /**
     * @RequestBody is bound to the method argument. HttpMessageConverter
     * resolves method argument depending on the content type.
     */
    @PostMapping
    public ResponseEntity<WebShopItem> add(@RequestBody WebShopItem catalogItem,
                                           UriComponentsBuilder uriComponentsBuilder) throws URISyntaxException {
        
        if (catalogItem.getId() == 0) {
            catalogItem.setId(catalogItems.size());
        }
        catalogItems.put((long) catalogItem.getId(), catalogItem);
        
        UriComponents uriComponents = uriComponentsBuilder.path(PATH + "/{id}").buildAndExpand(catalogItem.getId());
        return ResponseEntity.created(new URI(uriComponents.getPath())).body(catalogItem);
    }
}
