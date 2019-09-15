package com.deloitte.clothes_shop.ui.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;

import com.deloitte.clothes_shop.base.RichMediatorLiveData;
import com.deloitte.clothes_shop.data.api.ApiInterface;
import com.deloitte.clothes_shop.model.AddCartResponse;
import com.deloitte.clothes_shop.model.FailureResponse;
import com.deloitte.clothes_shop.model.ProductData;

import java.util.List;

import okhttp3.ResponseBody;

public class HomeViewModel extends ViewModel {

    private HomeRepo mHomeRepo = new HomeRepo();
    private Observer<Throwable> mErrorObserver;
    private Observer<Boolean> loading;
    private Observer<FailureResponse> mFailureObserver;
    private RichMediatorLiveData<List<ProductData>> mHomeLiveData;
    private RichMediatorLiveData<ProductData> mProductLiveData;
    private RichMediatorLiveData<AddCartResponse> addCartLiveData;
    private RichMediatorLiveData<ResponseBody> removeCartLiveData;


    //saving error & failure observers instance
    public void setGenericListeners(Observer<Throwable> errorObserver,
                                    Observer<FailureResponse> failureResponseObserver, Observer<Boolean> loading) {
        this.mErrorObserver = errorObserver;
        this.mFailureObserver = failureResponseObserver;
        this.loading = loading;
        initLiveData();
    }

    /**
     * Initilization of live data
     */
    private void initLiveData() {
        if (mHomeLiveData == null) {
            mHomeLiveData = new RichMediatorLiveData<List<ProductData>>() {
                @Override
                protected Observer<FailureResponse> getFailureObserver() {
                    return mFailureObserver;
                }

                @Override
                protected Observer<Throwable> getErrorObserver() {
                    return mErrorObserver;
                }
            };
        }
        if (mProductLiveData == null) {
            mProductLiveData = new RichMediatorLiveData<ProductData>() {
                @Override
                protected Observer<FailureResponse> getFailureObserver() {
                    return mFailureObserver;
                }

                @Override
                protected Observer<Throwable> getErrorObserver() {
                    return mErrorObserver;
                }
            };
        }
        if (addCartLiveData == null) {
            addCartLiveData = new RichMediatorLiveData<AddCartResponse>() {
                @Override
                protected Observer<FailureResponse> getFailureObserver() {
                    return mFailureObserver;
                }

                @Override
                protected Observer<Throwable> getErrorObserver() {
                    return mErrorObserver;
                }
            };
        }
        if (removeCartLiveData == null) {
            removeCartLiveData = new RichMediatorLiveData<ResponseBody>() {
                @Override
                protected Observer<FailureResponse> getFailureObserver() {
                    return mFailureObserver;
                }

                @Override
                protected Observer<Throwable> getErrorObserver() {
                    return mErrorObserver;
                }
            };
        }
    }

    //This function is used for getting product list
    public void getProductList() {
        loading.onChanged(true);
        mHomeRepo.getReviewRating(mHomeLiveData);
    }

    //This function is used for getting product Details
    public void getProductDetails(String productId) {
        loading.onChanged(true);
        mHomeRepo.getProductDetails(mProductLiveData, ApiInterface.BASE_URL + "products/" + productId);
    }

    //This function is used for add product into cart
    public void addItemToCart(String productId) {
        loading.onChanged(true);
        mHomeRepo.addItemToCart(addCartLiveData, productId);

    }

    //This function is used for remove product into cart
    public void removeItemFromCart(String cardId) {
        loading.onChanged(true);
        mHomeRepo.removeItemToCart(removeCartLiveData, ApiInterface.BASE_URL + "cart/" + cardId);

    }

    public RichMediatorLiveData<List<ProductData>> getmHomeLiveData() {
        return mHomeLiveData;
    }

    public RichMediatorLiveData<ProductData> getProductLiveData() {
        return mProductLiveData;
    }

    public RichMediatorLiveData<AddCartResponse> getAddCartLiveData() {
        return addCartLiveData;
    }

    public RichMediatorLiveData<ResponseBody> getRemoveCartLiveData() {
        return removeCartLiveData;
    }

}
