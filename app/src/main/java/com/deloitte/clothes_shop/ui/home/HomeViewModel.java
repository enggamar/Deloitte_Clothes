package com.deloitte.clothes_shop.ui.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;

import com.deloitte.clothes_shop.base.RichMediatorLiveData;
import com.deloitte.clothes_shop.data.api.ApiInterface;
import com.deloitte.clothes_shop.model.FailureResponse;
import com.deloitte.clothes_shop.model.ProductData;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private HomeRepo mHomeRepo = new HomeRepo();
    private Observer<Throwable> mErrorObserver;
    private Observer<Boolean> loading;
    private Observer<FailureResponse> mFailureObserver;
    private RichMediatorLiveData<List<ProductData>> mHomeLiveData;
    private RichMediatorLiveData<ProductData> mProductLiveData;


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
    }

    public void getProductList() {
        loading.onChanged(true);
        mHomeRepo.getReviewRating(mHomeLiveData);
    }

    public void getProductDetails(String productId) {
        loading.onChanged(true);
        mHomeRepo.getProductDetails(mProductLiveData, ApiInterface.BASE_URL + "products/" + productId);
    }

    public RichMediatorLiveData<List<ProductData>> getmHomeLiveData() {
        return mHomeLiveData;
    }

    public RichMediatorLiveData<ProductData> getProductLiveData() {
        return mProductLiveData;
    }

}
