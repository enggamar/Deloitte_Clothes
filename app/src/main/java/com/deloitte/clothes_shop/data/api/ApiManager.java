package com.deloitte.clothes_shop.data.api;


import android.support.annotation.NonNull;

import com.deloitte.clothes_shop.BuildConfig;
import com.deloitte.clothes_shop.model.ProductData;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiManager {

    private static final ApiManager instance = new ApiManager();
    private static OkHttpClient sOkHttpClient;
    private ApiInterface apiClient, authenticatedApiClient;
    private OkHttpClient.Builder httpClient;

    private ApiManager() {
        apiClient = getAuthenticatedRetrofitService();
        httpClient = getHttpClient();
    }

    public static ApiManager getInstance() {
        return instance;
    }

    private static ApiInterface getRetrofitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiInterface.BASE_URL)
                .build();

        return retrofit.create(ApiInterface.class);
    }

    private ApiInterface getAuthenticatedRetrofitService() {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(ApiInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = retrofitBuilder.client(getHttpClient().build()).build();
        return retrofit.create(ApiInterface.class);
    }

    /**
     * Method to create {@link OkHttpClient} builder by adding required headers in the {@link Request}
     *
     * @return OkHttpClient object
     */
    private OkHttpClient.Builder getHttpClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient().newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request original = chain.request();

                        Request request = original.newBuilder().addHeader("Content-Type", "application/json")

                                .method(original.method(), original.body())
                                .build();

                        return chain.proceed(request);
                    }
                });

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(logging);
        }
        sOkHttpClient = okHttpClientBuilder.build();
        return okHttpClientBuilder;
    }

    public Call<List<ProductData>> getProductList() {
        return apiClient.getProducts();
    }

    public Call<ProductData> getProductDetails(String productId) {
        return apiClient.getProductDetails(productId);
    }
}

