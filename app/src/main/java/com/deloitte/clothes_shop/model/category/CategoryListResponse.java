package com.deloitte.clothes_shop.model.category;



public class CategoryListResponse{



    private String productCategory;
    private int categoryIcon;

    public String getProductCategory() {
        return productCategory;
    }

    public int getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(int categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
