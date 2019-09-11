package com.deloitte.clothes_shop.model.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Appinventiv on 23-01-2019.
 */

public class CategoryResponse {
    @SerializedName("data")
    @Expose
    private ArrayList<CategoryListResponse> mCategory;
    private boolean showDialog;

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }

    public ArrayList<CategoryListResponse> getmCategory() {
        return mCategory;
    }

    public void setmCategory(ArrayList<CategoryListResponse> mCategory) {
        this.mCategory = mCategory;
    }
}
