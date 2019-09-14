package com.deloitte.clothes_shop.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deloitte.clothes_shop.database.WishListTable;
import com.deloitte.clothes_shop.databinding.AdapterCartViewBinding;
import com.deloitte.clothes_shop.databinding.AdapterWishListViewBinding;

import java.util.List;


public class WishListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private View.OnClickListener listener;
    private List<WishListTable> mList;

    public WishListAdapter(List<WishListTable> mList, View.OnClickListener listener) {
        this.listener = listener;
        this.mList = mList;
    }

    @NonNull
    @Override
    public WishListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        AdapterWishListViewBinding mBinding = AdapterWishListViewBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new WishListViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        WishListViewHolder holder = null;
        holder = (WishListViewHolder) viewHolder;
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class WishListViewHolder extends RecyclerView.ViewHolder {
        AdapterWishListViewBinding viewBinding;

        public WishListViewHolder(AdapterWishListViewBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
            viewBinding.ivBin.setOnClickListener(listener);
            viewBinding.llMain.setOnClickListener(listener);

        }

        //This function is used to set the values in XML
        public void bind(WishListTable bean) {
            viewBinding.ivBin.setTag(getAdapterPosition());
            viewBinding.setCartViewHolder(bean);
        }
    }
}