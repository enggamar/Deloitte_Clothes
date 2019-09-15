package com.deloitte.clothes_shop.ui.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.deloitte.clothes_shop.R;
import com.deloitte.clothes_shop.adapters.WishListAdapter;
import com.deloitte.clothes_shop.base.BaseActivity;
import com.deloitte.clothes_shop.database.DBHelper;
import com.deloitte.clothes_shop.database.WishListTable;
import com.deloitte.clothes_shop.databinding.ActivityCartBinding;

import java.util.ArrayList;
import java.util.List;

public class WishListActivity extends BaseActivity implements View.OnClickListener {


    private ActivityCartBinding mBinding;
    private List<WishListTable> mList;
    private WishListAdapter adapter;
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
        setUpListView();
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

        getWishList();
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WishListAdapter(mList, this);
        mBinding.rvList.setAdapter(adapter);
    }

    private void initView() {
        mBinding.includeHeader.tvTitle.setText(getResources().getString(R.string.wishlist));
        mBinding.includeHeader.ivWishlist.setVisibility(View.GONE);
        mBinding.includeHeader.ivCart.setOnClickListener(this);
        mBinding.includeHeader.ivBack.setOnClickListener(this);
    }

    //getting all Wishlisted item from DB
    private void getWishList() {
        mList = new ArrayList<>();
        mList.addAll(DBHelper.getDbHelper(this).queryHelperDao().getWishList());
        if (mList != null && mList.size() == 0) {
            mBinding.rvList.setVisibility(View.GONE);
            mBinding.icCartEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_cart:
                startActivity(new Intent(WishListActivity.this, CartActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_bin:
                postion = (int) v.getTag();
                DBHelper.getDbHelper(this).queryHelperDao().delete(mList.get(postion));
                removeItemList();
                break;
        }
    }


}