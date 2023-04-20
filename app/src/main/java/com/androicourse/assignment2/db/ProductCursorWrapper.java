package com.androicourse.assignment2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;

import com.androicourse.assignment2.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductCursorWrapper extends CursorWrapper {
    public ProductCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    private ContentValues getContentValues(Product product) {
        ContentValues values = new ContentValues();
        values.put(DatabaseSchemas.ProductTable.Cols.UUID, product.getId());
        values.put(DatabaseSchemas.ProductTable.Cols.THUMBNAIL, product.getThumbnail());
        values.put(DatabaseSchemas.ProductTable.Cols.NAME, product.getName());
        values.put(DatabaseSchemas.ProductTable.Cols.CATEGORY, product.getCategory());
        values.put(DatabaseSchemas.ProductTable.Cols.UNIT_PRICE, product.getUnitPrice());
        values.put(DatabaseSchemas.ProductTable.Cols.QUANTITY, product.getQuantity());
        return values;
    }

    public Product getProduct() {
        moveToFirst();
        Product product = new Product();
        if (getCount() == 0) {
            return null;
        }

        int id = getInt(getColumnIndex(DatabaseSchemas.ProductTable.Cols.UUID));
        String urlImage = getString(getColumnIndex(DatabaseSchemas.ProductTable.Cols.THUMBNAIL));
        String name = getString(getColumnIndex(DatabaseSchemas.ProductTable.Cols.NAME));
        String category = getString(getColumnIndex(DatabaseSchemas.ProductTable.Cols.CATEGORY));
        int price = getInt(getColumnIndex(DatabaseSchemas.ProductTable.Cols.UNIT_PRICE));
        int quantity = getInt(getColumnIndex(DatabaseSchemas.ProductTable.Cols.QUANTITY));

        if (id != 0 && valid(name) && valid(urlImage) && price > 0 && quantity > 0)
            product = new Product(id, urlImage, name, category, price, quantity);
        return product;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        moveToFirst();
        while (!isAfterLast()) {
            Product product = getProduct();
            products.add(product);
            moveToNext();
        }
        return products;
    }

    private boolean valid(String string) {
        return string != null && string.length() > 0;
    }
}
