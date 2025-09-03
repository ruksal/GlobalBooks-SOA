package com.globalbooks.catalog;
public class SearchRequest {
    private String query;
    private Integer limit;
    public String getQuery(){ return query; }
    public void setQuery(String query){ this.query = query; }
    public Integer getLimit(){ return limit; }
    public void setLimit(Integer limit){ this.limit = limit; }
}
