package com.globalbooks.catalog;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace="http://globalbooks.com/catalog/v1", name="CatalogPortType", serviceName="CatalogService")
public interface CatalogService {
    @WebMethod
    GetBookResponse getBook(@WebParam(name="GetBookRequest") GetBookRequest req);

    @WebMethod
    SearchResponse search(@WebParam(name="SearchRequest") SearchRequest req);
}
