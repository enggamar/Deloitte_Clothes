package com.deloitte.clothes_shop.ui.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.deloitte.clothes_shop.R;
import com.deloitte.clothes_shop.adapters.CartListAdapter;
import com.deloitte.clothes_shop.base.BaseActivity;
import com.deloitte.clothes_shop.database.CartTable;
import com.deloitte.clothes_shop.database.DBHelper;
import com.deloitte.clothes_shop.databinding.ActivityCartBinding;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class CartActivity extends BaseActivity implements View.OnClickListener {


    private ActivityCartBinding mBinding;
    private List<CartTable> mList;
    private CartListAdapter adapter;
    private HomeViewModel mHomeViewModel;
    int postion;

    @Override
    protected int getResourceId() {
        return R.layout.activity_cart;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_cart);
        initView();
        setUpViewModel();
        setUpListView();
    }

    private void setUpViewModel() {
        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        mHomeViewModel.setGenericListeners(getErrorObserver(), getFailureResponseObserver(), getLoadingStateObserver());
        mHomeViewModel.getRemoveCartLiveData().observe(this, new Observer<ResponseBody>() {
            @Override
            public void onChanged(@Nullable ResponseBody s) {
                getLoadingStateObserver().onChanged(false);
                DBHelper.getDbHelper(CartActivity.this).queryHelperDao().delete(mList.get(postion));
                removeItemList();
            }
        });
    }

    private void removeItemList() {
        try {
            mList.remove(postion);
            if (mList.size() == 0) {
                mBinding.icCartEmpty.setVisibility(View.VISIBLE);
                mBinding.rvList.setVisibility(View.GONE);
            } else {
                mBinding.icCartEmpty.setVisibility(View.GONE);
                mBinding.rvList.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {

        }
    }


    private void setUpListView() {

        getCartListDb();
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CartListAdapter(mList, this);
        mBinding.rvList.setAdapter(adapter);
    }

    private void initView() {
        mBinding.includeHeader.tvTitle.setText(getString(R.string.cart));
        mBinding.includeHeader.ivWishlist.setVisibility(View.GONE);
        mBinding.includeHeader.ivCart.setImageDrawable(getResources().getDrawable(R.drawable.ic_unselected_wishlist));
        mBinding.includeHeader.ivCart.setOnClickListener(this);
        mBinding.includeHeader.ivBack.setOnClickListener(this);
    }

    private void getCartListDb() {
        mList = new ArrayList<>();
        mList.addAll(DBHelper.getDbHelper(this).queryHelperDao().getCart());
        if (mList != null && mList.size() == 0) {
            mBinding.rvList.setVisibility(View.GONE);
            mBinding.icCartEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_cart:
                startActivity(new Intent(CartActivity.this, WishListActivity.class));

                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_bin:
                postion = (int) v.getTag();
                mHomeViewModel.removeItemFromCart(mList.get(postion).getCartId());
                break;
        }
    }


}