package com.globalbooks.catalog;
import java.math.BigDecimal;
public class SearchItem {
    private String isbn;
    private String title;
    private BigDecimal price;
    public String getIsbn(){ return isbn; }
    public void setIsbn(String isbn){ this.isbn=isbn;}
    public String getTitle(){ return title; }
    public void setTitle(String title){ this.title=title; }
    public BigDecimal getPrice(){ return price; }
    public void setPrice(BigDecimal price){ this.price=price; }
}
