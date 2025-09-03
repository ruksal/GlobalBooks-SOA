package com.globalbooks.catalog;
import java.util.List;
public class SearchResponse {
    private List<SearchItem> items;
    public List<SearchItem> getItems(){ return items; }
    public void setItems(List<SearchItem> items){ this.items = items; }
}
