package com.globalbooks.catalog;
import java.math.BigDecimal;
public class GetBookResponse {
    private String isbn;
    private String title;
    private BigDecimal price;
    private String currency;
    private int inventory;
    public String getIsbn(){ return isbn; }
    public void setIsbn(String isbn){ this.isbn = isbn; }
    public String getTitle(){ return title; }
    public void setTitle(String title){ this.title = title; }
    public BigDecimal getPrice(){ return price; }
    public void setPrice(BigDecimal price){ this.price = price; }
    public String getCurrency(){ return currency; }
    public void setCurrency(String currency){ this.currency = currency; }
    public int getInventory(){ return inventory; }
    public void setInventory(int inventory){ this.inventory = inventory; }
}
