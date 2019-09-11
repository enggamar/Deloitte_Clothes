package com.deloitte.clothes_shop.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deloitte.clothes_shop.R;
import com.deloitte.clothes_shop.adapters.CategoryListAdapter;
import com.deloitte.clothes_shop.base.BaseFragment;
import com.deloitte.clothes_shop.databinding.FragmentCategoryBinding;
import com.deloitte.clothes_shop.model.category.CategoryListResponse;

import java.util.ArrayList;

public class CategoryFragment extends BaseFragment implements View.OnClickListener {
    private ArrayList<CategoryListResponse> mCategoryList;
    private CategoryListAdapter adapter;
    private FragmentCategoryBinding mBinding;
    private Activity mActivity;
    private ICategoryHost iCategoryHost;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = FragmentCategoryBinding.inflate(inflater);
        initView();
        return mBinding.getRoot();
    }

    private void initView() {
        mActivity = getActivity();
        setupView();
    }

    private void setupView() {
        setCategoryData();

        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 2);
        adapter = new CategoryListAdapter(mCategoryList, this);
        mBinding.rvCategoryList.setLayoutManager(layoutManager);
        mBinding.rvCategoryList.setAdapter(adapter);
    }

    private void setCategoryData() {
        mCategoryList = new ArrayList<>();
        String[] categoryName = getResources().getStringArray(R.array.category_name);
        TypedArray categoryIcon = getResources().obtainTypedArray(R.array.category_icon);
        for (int i = 0; i < categoryName.length; i++) {
            CategoryListResponse model = new CategoryListResponse();
            model.setCategoryIcon(categoryIcon.getResourceId(i, -1));
            model.setProductCategory(categoryName[i]);
            mCategoryList.add(model);
        }
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        iCategoryHost.openProductList(mCategoryList.get(position).getProductCategory());
    }

    interface ICategoryHost {

        public void openProductList(String str);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ICategoryHost) {
            iCategoryHost = (ICategoryHost) context;
        } else
            throw new IllegalStateException("host must implement ILoginHost");
    }
}
