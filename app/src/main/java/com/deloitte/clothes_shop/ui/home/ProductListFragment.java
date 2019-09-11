package com.deloitte.clothes_shop.ui.home;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deloitte.clothes_shop.R;
import com.deloitte.clothes_shop.adapters.ProductListAdapter;
import com.deloitte.clothes_shop.base.BaseFragment;
import com.deloitte.clothes_shop.constants.AppConstants;
import com.deloitte.clothes_shop.databinding.FragmentCategoryBinding;
import com.deloitte.clothes_shop.model.ProductData;

import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends BaseFragment implements View.OnClickListener {

    private FragmentCategoryBinding mBinding;
    private String productCategoryId;
    private Activity mActivity;
    private ProductListAdapter adapter;
    private List<ProductData> mList;
    private HomeViewModel mHomeViewModel;
    private ProductListHost iProductListHost;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentCategoryBinding.inflate(inflater);
        initView();
        return mBinding.getRoot();
    }

    private void initView() {
        mActivity = getActivity();
        getArgumentData();
        setupView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        mHomeViewModel.setGenericListeners(getErrorObserver(), getFailureResponseObserver(), getLoadingStateObserver());
        mHomeViewModel.getmHomeLiveData().observe(this, new Observer<List<ProductData>>() {
            @Override
            public void onChanged(@Nullable List<ProductData> productData) {
                getLoadingStateObserver().onChanged(false);
                getData(productData);
            }
        });
        mHomeViewModel.getProductList();
    }

    private void getData(List<ProductData> productData) {
        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductCategory().equalsIgnoreCase(productCategoryId)) {
                mList.add(productData.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void setupView() {
        mList = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 2);
        adapter = new ProductListAdapter(mList, this);
        mBinding.rvCategoryList.setLayoutManager(layoutManager);
        mBinding.rvCategoryList.setAdapter(adapter);
    }

    private void getArgumentData() {
        productCategoryId = getArguments().getString(AppConstants.BUNDLE_DATA);
    }

    @Override
    public void onClick(View v) {
        int postion = (int) v.getTag();
        switch (v.getId()) {
            case R.id.iv_wishlist:
                wishlistClicked(postion);
                break;
            case R.id.card_product:
                openProductDetails(postion);
                break;
        }

    }

    private void openProductDetails(int postion) {
        iProductListHost.openProductDetails(mList.get(postion).getProductId(), mList.get(postion).getProductName());
    }

    private void wishlistClicked(int postion) {
        if (mList.get(postion).isSelected()) {
            mList.get(postion).setSelected(false);
        } else {
            mList.get(postion).setSelected(true);
        }
        adapter.notifyItemChanged(postion);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ProductListHost) {
            iProductListHost = (ProductListHost) context;
        } else
            throw new IllegalStateException("host must implement ILoginHost");
    }

    interface ProductListHost {
        public void openProductDetails(String productId, String productName);
    }
}
