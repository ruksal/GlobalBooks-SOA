package com.globalbooks.catalog;

import javax.jws.WebService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import jakarta.jws.HandlerChain;

@WebService(endpointInterface="com.globalbooks.catalog.CatalogService",
            targetNamespace="http://globalbooks.com/catalog/v1",
            serviceName="CatalogService")
@HandlerChain(file = "handler-chain.xml")
public class CatalogServiceImpl implements CatalogService {

    @Override
    public GetBookResponse getBook(GetBookRequest req) {
        // In a real app you'd query DB. Here return a stub
        GetBookResponse r = new GetBookResponse();
        r.setIsbn(req.getIsbn());
        r.setTitle("Sample Book Title");
        r.setPrice(new BigDecimal("19.99"));
        r.setCurrency("USD");
        r.setInventory(42);
        return r;
    }

    @Override
    public SearchResponse search(SearchRequest req) {
        SearchResponse r = new SearchResponse();
        List<SearchItem> items = new ArrayList<>();
        SearchItem it = new SearchItem();
        it.setIsbn("9781234567897");
        it.setTitle("Sample Book Title");
        it.setPrice(new BigDecimal("19.99"));
        items.add(it);
        r.setItems(items);
        return r;
    }
}
