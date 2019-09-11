package com.deloitte.clothes_shop.data.api;


import com.deloitte.clothes_shop.model.ProductData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by appinventiv on 27/3/18.
 */

public interface ApiInterface {


    String BASE_URL = "https://private-anon-72989785ae-ddshop.apiary-mock.com/";


    @GET("products")
    Call<List<ProductData>> getProducts();

    @GET
    Call<ProductData> getProductDetails(@Url String url);
}

