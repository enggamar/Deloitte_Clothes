package com.deloitte.clothes_shop.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deloitte.clothes_shop.database.CartTable;
import com.deloitte.clothes_shop.databinding.AdapterCartViewBinding;

import java.util.List;


public class CartListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private View.OnClickListener listener;
    private List<CartTable> mList;

    public CartListAdapter(List<CartTable> mList, View.OnClickListener listener) {
        this.listener = listener;
        this.mList = mList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        AdapterCartViewBinding mBinding = AdapterCartViewBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new CartViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        CartViewHolder holder = null;
        holder = (CartViewHolder) viewHolder;
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class CartViewHolder extends RecyclerView.ViewHolder {
        AdapterCartViewBinding viewBinding;

        public CartViewHolder(AdapterCartViewBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
            viewBinding.ivBin.setOnClickListener(listener);
            viewBinding.llMain.setOnClickListener(listener);

        }

        //This function is used to set the values in XML
        public void bind(CartTable bean) {
            viewBinding.ivBin.setTag(getAdapterPosition());
            viewBinding.setCartViewHolder(bean);
        }
    }
}