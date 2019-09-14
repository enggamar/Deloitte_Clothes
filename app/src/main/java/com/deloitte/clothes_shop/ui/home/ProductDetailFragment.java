package com.deloitte.clothes_shop.ui.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deloitte.clothes_shop.R;
import com.deloitte.clothes_shop.base.BaseFragment;
import com.deloitte.clothes_shop.constants.AppConstants;
import com.deloitte.clothes_shop.database.CartTable;
import com.deloitte.clothes_shop.database.DBHelper;
import com.deloitte.clothes_shop.database.WishListTable;
import com.deloitte.clothes_shop.databinding.FragmentProductDetailsBinding;
import com.deloitte.clothes_shop.model.AddCartResponse;
import com.deloitte.clothes_shop.model.ProductData;

public class ProductDetailFragment extends BaseFragment implements View.OnClickListener {

    FragmentProductDetailsBinding mBinding;
    private HomeViewModel mHomeViewModel;
    private String productId;
    private ProductData data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentProductDetailsBinding.inflate(inflater);
        initView();
        return mBinding.getRoot();
    }

    private void initView() {

        setListener();

    }

    private void setListener() {
        mBinding.ivWishlist.setOnClickListener(this);
        mBinding.tvAddToCart.setOnClickListener(this);
        mBinding.tvCheckout.setOnClickListener(this);
        mBinding.ivWishlist.setSelected(false);

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
                data = productData;
                setData(productData);
            }
        });
        mHomeViewModel.getAddCartLiveData().observe(this, new Observer<AddCartResponse>() {
            @Override
            public void onChanged(@Nullable AddCartResponse addCartResponse) {
                getLoadingStateObserver().onChanged(false);
                AddDataToDB(data, addCartResponse);

            }
        });
        getArgumentData();
        mHomeViewModel.getProductDetails(productId);
    }

    // Add Cart data to DB
    private void AddDataToDB(ProductData data, AddCartResponse addCartResponse) {
        CartTable cart = cartModel(data, addCartResponse);
        DBHelper.getDbHelper(getActivity()).queryHelperDao().insertAll(cart);
    }

    //Create Cart model
    private CartTable cartModel(ProductData data, AddCartResponse addCartResponse) {
        CartTable cartTable = new CartTable();
        cartTable.setCartId(addCartResponse.getCardId());
        cartTable.setProductId(addCartResponse.getProductId());
        cartTable.setPrice(data.getPrice());
        cartTable.setOldPrice(data.getOldPrice());
        cartTable.setProductCategory(data.getProductCategory());
        cartTable.setProductName(data.getProductName());
        cartTable.setStock(data.getStock());
        return cartTable;
    }

    private void setData(ProductData productData) {
        mBinding.setViewModel(productData);
    }

    //get Argument Data
    private void getArgumentData() {
        productId = getArguments().getString(AppConstants.BUNDLE_DATA);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_wishlist:
                if (data != null)
                    setWishlist();
                break;
            case R.id.tv_add_to_cart:
                mHomeViewModel.addItemToCart(productId);
                break;
            case R.id.tv_checkout:
                break;
        }
    }

    private void setWishlist() {
        if (!mBinding.ivWishlist.isSelected()) {
            mBinding.ivWishlist.setSelected(true);
            addWishListItemTODBpostion();
        } else {
            removeWishListFormDB();
            mBinding.ivWishlist.setSelected(false);
        }
    }


    //Remove Wishlist from DB
    private void removeWishListFormDB() {
        WishListTable wishListTable = wishListModel();
        DBHelper.getDbHelper(getActivity()).queryHelperDao().delete(wishListTable);

    }

    //Insert wishlist into DB
    private void addWishListItemTODBpostion() {
        WishListTable wishListTable = wishListModel();
        DBHelper.getDbHelper(getActivity()).queryHelperDao().insertAll(wishListTable);
    }


    //create wishlist model
    private WishListTable wishListModel() {
        WishListTable wishListTable = new WishListTable();
        wishListTable.setProductName(data.getProductName());
        wishListTable.setProductCategory(data.getProductCategory());
        wishListTable.setPrice(data.getPrice());
        wishListTable.setProductId(data.getProductId());
        wishListTable.setOldPrice(data.getOldPrice());
        wishListTable.setStock(data.getStock());

        return wishListTable;
    }

}
