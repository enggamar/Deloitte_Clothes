package com.deloitte.clothes_shop.data.api;


import com.deloitte.clothes_shop.model.AddCartResponse;
import com.deloitte.clothes_shop.model.ProductData;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiInterface {


    String BASE_URL = "https://private-anon-72989785ae-ddshop.apiary-mock.com/";


    @GET("products")
    Call<List<ProductData>> getProducts();

    @GET
    Call<ProductData> getProductDetails(@Url String url);

    @FormUrlEncoded
    @POST("cart")
    Call<AddCartResponse> addItemToCart(@Field("productId") String productId);

    @DELETE
    Call<ResponseBody> deleteItemCart(@Url String url);

}

