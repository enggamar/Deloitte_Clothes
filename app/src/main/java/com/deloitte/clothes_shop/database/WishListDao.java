package com.deloitte.clothes_shop.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WishListDao {

    //Insert Cart Data in DB
    @Insert
    void insertAll(WishListTable wishListData);

    //Get All Cart Data
    @Query("SELECT * FROM cart")
    List<WishListTable> getAll();

    //Remove Cart item
    @Delete
    void delete(CartTable cart);
}
