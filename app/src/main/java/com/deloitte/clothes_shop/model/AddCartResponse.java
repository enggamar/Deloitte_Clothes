package com.deloitte.clothes_shop.model;

import com.google.gson.annotations.SerializedName;

public class AddCartResponse {

    @SerializedName("cartId")
    private String cardId;
    @SerializedName("productId")
    private String productId;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
