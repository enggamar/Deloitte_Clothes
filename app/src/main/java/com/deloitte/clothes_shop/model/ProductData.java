package com.deloitte.clothes_shop.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Amar
 */

public class ProductData implements Parcelable {

    @SerializedName("productId")
    private String productId;
    @SerializedName("name")
    private String productName;
    @SerializedName("category")
    private String productCategory;
    @SerializedName("price")
    private String price;
    @SerializedName("oldPrice")
    private String oldPrice;
    @SerializedName("stock")
    private int stock;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ProductData() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.productId);
        dest.writeString(this.productName);
        dest.writeString(this.productCategory);
        dest.writeString(this.price);
        dest.writeString(this.oldPrice);
        dest.writeInt(this.stock);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    protected ProductData(Parcel in) {
        this.productId = in.readString();
        this.productName = in.readString();
        this.productCategory = in.readString();
        this.price = in.readString();
        this.oldPrice = in.readString();
        this.stock = in.readInt();
        this.isSelected = in.readByte() != 0;
    }

    public static final Creator<ProductData> CREATOR = new Creator<ProductData>() {
        @Override
        public ProductData createFromParcel(Parcel source) {
            return new ProductData(source);
        }

        @Override
        public ProductData[] newArray(int size) {
            return new ProductData[size];
        }
    };
}
