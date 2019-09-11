package com.deloitte.clothes_shop.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deloitte.clothes_shop.databinding.AdapterProductViewBinding;
import com.deloitte.clothes_shop.model.ProductData;

import java.util.List;


public class ProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private View.OnClickListener listener;
    private List<ProductData> mList;

    public ProductListAdapter(List<ProductData> mList, View.OnClickListener listener) {
        this.listener = listener;
        this.mList = mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        AdapterProductViewBinding mBinding = AdapterProductViewBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new ProductViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ProductViewHolder holder = null;
        holder = (ProductViewHolder) viewHolder;
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class ProductViewHolder extends RecyclerView.ViewHolder {
        AdapterProductViewBinding viewBinding;

        public ProductViewHolder(AdapterProductViewBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
            viewBinding.ivWishlist.setOnClickListener(listener);
            viewBinding.cardProduct.setOnClickListener(listener);
        }

        //This function is used to set the values in XML
        public void bind(ProductData bean) {
            viewBinding.ivWishlist.setTag(getAdapterPosition());
            viewBinding.cardProduct.setTag(getAdapterPosition());
            viewBinding.setViewModel(bean);
        }
    }
}