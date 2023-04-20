package com.androicourse.assignment2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.androicourse.assignment2.models.Product;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cart.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DatabaseSchemas.ProductTable.NAME + "("
                + DatabaseSchemas.ProductTable.Cols.UUID + " INT, "
                + DatabaseSchemas.ProductTable.Cols.NAME + " TEXT,"
                + DatabaseSchemas.ProductTable.Cols.THUMBNAIL + " TEXT,"
                + DatabaseSchemas.ProductTable.Cols.UNIT_PRICE + " INT,"
                + DatabaseSchemas.ProductTable.Cols.CATEGORY + " INT,"
                + DatabaseSchemas.ProductTable.Cols.QUANTITY + " INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS cart");
        onCreate(db);
    }
}
