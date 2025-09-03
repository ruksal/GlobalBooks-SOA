package com.globalbooks.orders.model;
public class Order {
    private String id;
    private String status;
    private double total;
    private String currency;
    private String createdAt;
    private String paymentRef;
    private String shippingRef;
    // getters/setters
    public String getId(){return id;} public void setId(String i){this.id=i;}
    public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
    public double getTotal(){return total;} public void setTotal(double t){this.total=t;}
    public String getCurrency(){return currency;} public void setCurrency(String c){this.currency=c;}
    public String getCreatedAt(){return createdAt;} public void setCreatedAt(String c){this.createdAt=c;}
    public String getPaymentRef(){return paymentRef;} public void setPaymentRef(String p){this.paymentRef=p;}
    public String getShippingRef(){return shippingRef;} public void setShippingRef(String s){this.shippingRef=s;}
}
