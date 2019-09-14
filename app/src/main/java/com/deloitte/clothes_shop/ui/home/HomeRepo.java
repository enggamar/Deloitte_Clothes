package com.deloitte.clothes_shop.ui.home;

import com.deloitte.clothes_shop.base.NetworkCallback;
import com.deloitte.clothes_shop.base.RichMediatorLiveData;
import com.deloitte.clothes_shop.data.api.ApiManager;
import com.deloitte.clothes_shop.model.AddCartResponse;
import com.deloitte.clothes_shop.model.FailureResponse;
import com.deloitte.clothes_shop.model.ProductData;

import java.util.List;

import okhttp3.ResponseBody;

public class HomeRepo {


    // This Api is use for get Product List
    public void getReviewRating(final RichMediatorLiveData<List<ProductData>> liveData) {
        ApiManager.getInstance().getProductList().enqueue(new NetworkCallback<List<ProductData>>() {
            @Override
            public void onSuccess(List<ProductData> successResponse) {
                if (successResponse != null) {
                    liveData.setValue(successResponse);
                }
            }

            @Override
            public void onFailure(FailureResponse failureResponse) {
                liveData.setFailure(failureResponse);
            }

            @Override
            public void onError(Throwable t) {
                liveData.setError(t);
            }
        });
    }

    // This Api is use for get ProductDetails
    public void getProductDetails(final RichMediatorLiveData<ProductData> liveData, String productId) {
        ApiManager.getInstance().getProductDetails(productId).enqueue(new NetworkCallback<ProductData>() {
            @Override
            public void onSuccess(ProductData successResponse) {
                if (successResponse != null) {
                    liveData.setValue(successResponse);
                }
            }

            @Override
            public void onFailure(FailureResponse failureResponse) {
                liveData.setFailure(failureResponse);
            }

            @Override
            public void onError(Throwable t) {
                liveData.setError(t);
            }
        });
    }

    // This Api is use for add Item to cart
    public void addItemToCart(final RichMediatorLiveData<AddCartResponse> liveData, String productId) {
        ApiManager.getInstance().addItemToCart(productId).enqueue(new NetworkCallback<AddCartResponse>() {
            @Override
            public void onSuccess(AddCartResponse successResponse) {
                if (successResponse != null) {
                    liveData.setValue(successResponse);
                }
            }

            @Override
            public void onFailure(FailureResponse failureResponse) {
                liveData.setFailure(failureResponse);
            }

            @Override
            public void onError(Throwable t) {
                liveData.setError(t);
            }
        });
    }


    // This Api is use for remove Item to card
    public void removeItemToCart(final RichMediatorLiveData<ResponseBody> liveData, String cardId) {
        ApiManager.getInstance().removeItemFromCart(cardId).enqueue(new NetworkCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody successResponse) {
//                if (successResponse != null) {
                    liveData.setValue(successResponse);
//                }
            }

            @Override
            public void onFailure(FailureResponse failureResponse) {
                liveData.setFailure(failureResponse);
            }

            @Override
            public void onError(Throwable t) {
                liveData.setError(t);
            }
        });
    }

}
