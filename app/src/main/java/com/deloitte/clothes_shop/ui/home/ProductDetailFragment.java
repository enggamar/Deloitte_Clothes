package com.deloitte.clothes_shop.ui.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deloitte.clothes_shop.base.BaseFragment;
import com.deloitte.clothes_shop.constants.AppConstants;
import com.deloitte.clothes_shop.data.api.ApiInterface;
import com.deloitte.clothes_shop.databinding.FragmentProductDetailsBinding;
import com.deloitte.clothes_shop.model.ProductData;

public class ProductDetailFragment extends BaseFragment {

    FragmentProductDetailsBinding mBinding;
    private HomeViewModel mHomeViewModel;
    private String productId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentProductDetailsBinding.inflate(inflater);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        mHomeViewModel.setGenericListeners(getErrorObserver(), getFailureResponseObserver(), getLoadingStateObserver());
        mHomeViewModel.getProductLiveData().observe(this, new Observer<ProductData>() {
            @Override
            public void onChanged(@Nullable ProductData productData) {
                getLoadingStateObserver().onChanged(false);
                setData(productData);
            }
        });
        getArgumentData();
        mHomeViewModel.getProductDetails(productId);
    }

    private void setData(ProductData productData) {
        mBinding.setViewModel(productData);
    }

    private void getArgumentData() {
        productId = getArguments().getString(AppConstants.BUNDLE_DATA);
    }
}
