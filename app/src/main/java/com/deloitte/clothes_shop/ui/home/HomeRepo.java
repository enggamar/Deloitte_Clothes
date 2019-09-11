package com.deloitte.clothes_shop.ui.home;

import com.deloitte.clothes_shop.base.NetworkCallback;
import com.deloitte.clothes_shop.base.RichMediatorLiveData;
import com.deloitte.clothes_shop.data.api.ApiManager;
import com.deloitte.clothes_shop.model.FailureResponse;
import com.deloitte.clothes_shop.model.ProductData;

import java.util.List;

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

}
