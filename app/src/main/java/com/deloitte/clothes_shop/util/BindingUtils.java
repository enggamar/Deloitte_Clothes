package com.deloitte.clothes_shop.util;

import android.databinding.BindingAdapter;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.deloitte.clothes_shop.R;

/**
 * Created by Amar
 */

public class BindingUtils {

    @BindingAdapter("bindImage")
    public static void bindImage(AppCompatImageView view, int resourceId) {
        view.setImageResource(resourceId);
    }

    @BindingAdapter("bindPrice")
    public static void bindPrice(AppCompatTextView view, String price) {
        view.setText(String.format("%s $%s", view.getContext().getString(R.string.price), price));
    }

    @BindingAdapter("bindOldPrice")
    public static void bindOldPrice(AppCompatTextView view, String price) {
        if (price == null) {
            view.setVisibility(View.GONE);
        } else {

            view.setVisibility(View.VISIBLE);
            view.setText("" + price);
            view.setPaintFlags(view.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    @BindingAdapter("isOutOfStock")
    public static void isOutOfStock(AppCompatImageView view, int available) {
        if (available > 0) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    @BindingAdapter("isSelectedWishlist")
    public static void isSelectedWishlist(AppCompatImageView view, boolean isSelected) {
        if (!isSelected) {
            view.setSelected(false);
        } else {
            view.setSelected(true);
        }
    }

    @BindingAdapter("bindStock")
    public static void bindStock(AppCompatTextView view, int stock) {
        if (stock > 0) {
            view.setText("" + stock);
        } else {
            view.setText(view.getContext().getString(R.string.out_of_stock));
            view.setTextColor(view.getContext().getResources().getColor(R.color.red));
        }
    }

    @BindingAdapter("checkOutOfStock")
    public static void checkOutOfStock(AppCompatTextView view, int stock) {
        if (stock > 0) {
            view.setTextColor(view.getContext().getResources().getColor(R.color.colorPrimary));
            view.setBackgroundDrawable(view.getContext().getResources().getDrawable(R.drawable.round_border_color_primary));
            view.setText(view.getContext().getResources().getString(R.string.add_to_cart));
            view.setEnabled(true);
        } else {
            view.setText(view.getContext().getString(R.string.out_of_stock));
            view.setTextColor(view.getContext().getResources().getColor(R.color.DarkGray));
            view.setBackgroundDrawable(view.getContext().getResources().getDrawable(R.drawable.round_border_gray));
            view.setEnabled(false);
        }
    }

}

