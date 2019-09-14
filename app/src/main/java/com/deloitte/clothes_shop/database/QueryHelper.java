package com.deloitte.clothes_shop.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface QueryHelper {

    //Insert Cart Data in DB
    @Insert
    void insertAll(CartTable cartData);

    //Get All Cart Data
    @Query("SELECT * FROM cart")
    List<CartTable> getCart();

    //Remove Cart item
    @Delete
    void delete(CartTable cart);

    //InsetData To WishList
    @Insert
    void insertAll(WishListTable wishList);

    //Get All Cart Data
    @Query("SELECT * FROM wishList")
    List<WishListTable> getWishList();

    //Remove WishList item
    @Delete
    void delete(WishListTable wishListTable);


}
