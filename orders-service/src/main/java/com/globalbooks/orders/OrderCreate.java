package com.globalbooks.orders.model;
import java.util.List;

public class OrderCreate {
    private String customerId;
    private List<Item> items;
    private Address shippingAddress;
    // getters / setters

    public String getCustomerId(){return customerId;}
    public void setCustomerId(String c){this.customerId=c;}
    public List<Item> getItems(){return items;}
    public void setItems(List<Item> items){this.items=items;}
    public Address getShippingAddress(){return shippingAddress;}
    public void setShippingAddress(Address a){this.shippingAddress=a;}

    public static class Item {
        private String isbn; private int quantity; private double unitPrice; private String currency;
        public String getIsbn(){return isbn;} public void setIsbn(String i){this.isbn=i;}
        public int getQuantity(){return quantity;} public void setQuantity(int q){this.quantity=q;}
        public double getUnitPrice(){return unitPrice;} public void setUnitPrice(double p){this.unitPrice=p;}
        public String getCurrency(){return currency;} public void setCurrency(String c){this.currency=c;}
    }
    public static class Address {
        private String line1; private String line2; private String city; private String country; private String postalCode;
        public String getLine1(){return line1;} public void setLine1(String l){this.line1=l;}
        public String getLine2(){return line2;} public void setLine2(String l){this.line2=l;}
        public String getCity(){return city;} public void setCity(String c){this.city=c;}
        public String getCountry(){return country;} public void setCountry(String c){this.country=c;}
        public String getPostalCode(){return postalCode;} public void setPostalCode(String p){this.postalCode=p;}
    }
}
