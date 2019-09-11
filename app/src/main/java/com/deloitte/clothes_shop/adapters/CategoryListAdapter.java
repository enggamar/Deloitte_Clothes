package com.deloitte.clothes_shop.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deloitte.clothes_shop.databinding.LayoutCategoryViewBinding;
import com.deloitte.clothes_shop.model.category.CategoryListResponse;


import java.util.ArrayList;


public class CategoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private View.OnClickListener listener;
    private ArrayList<CategoryListResponse> mCategoryList;

    public CategoryListAdapter(ArrayList<CategoryListResponse> mCategoryList, View.OnClickListener listener) {
        this.listener = listener;
        this.mCategoryList = mCategoryList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutCategoryViewBinding mBinding = LayoutCategoryViewBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new CategoryListViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        CategoryListViewHolder holder = null;
        holder = (CategoryListViewHolder) viewHolder;
        holder.bind(mCategoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

    private class CategoryListViewHolder extends RecyclerView.ViewHolder {
        LayoutCategoryViewBinding viewBinding;

        public CategoryListViewHolder(LayoutCategoryViewBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
            viewBinding.cardCategory.setOnClickListener(listener);
        }

        //This function is used to set the values in XML
        public void bind(CategoryListResponse bean) {
            viewBinding.cardCategory.setTag(getAdapterPosition());
            viewBinding.setViewModel(bean);
        }
    }
}