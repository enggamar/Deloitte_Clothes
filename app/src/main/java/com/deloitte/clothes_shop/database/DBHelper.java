package com.deloitte.clothes_shop.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {CartTable.class, WishListTable.class}, version = 2, exportSchema = false)
public abstract class DBHelper extends RoomDatabase {

    private static DBHelper INSTANCE;

    public abstract QueryHelper queryHelperDao();

    public static DBHelper getDbHelper(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), DBHelper.class, "clothes_store")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}