package com.androicourse.assignment2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androicourse.assignment2.models.Product;

import java.util.List;

public class ProductManager {
    private static ProductManager sProductManager;
    private static final String INSERT = "INSERT INTO" +
            DatabaseSchemas.ProductTable.NAME + "("
            + DatabaseSchemas.ProductTable.Cols.NAME + ")" + "VALUES(?)";

    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase db;

    private ProductManager(Context context) {
        mDatabaseHelper = new DatabaseHelper(context);
        db = mDatabaseHelper.getWritableDatabase();
    }

    public static ProductManager getInstance(Context context) {
        if (sProductManager == null) {
            sProductManager = new ProductManager(context);
        }
        return sProductManager;
    }

    public DatabaseHelper getDatabaseHelper() {
        return mDatabaseHelper;
    }

    public List<Product> all(){
        String sql="SELECT*FROM "+DatabaseSchemas.ProductTable.NAME;
        Cursor cursor = db.rawQuery(sql, null);
        ProductCursorWrapper noteCursorWrapper = new ProductCursorWrapper(cursor);
        return noteCursorWrapper.getAllProducts();
    }

    public Product findProductById(int id) {
        String sql = "SELECT * FROM " + DatabaseSchemas.ProductTable.NAME
                + " WHERE id = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{id + ""});
        ProductCursorWrapper noteCursorWrapper = new ProductCursorWrapper(cursor);
        return noteCursorWrapper.getProduct();
    }

    public boolean update(Product product) {
        ContentValues contentValues = new ContentValues();

        int id = product.getId();
        String idString = String.valueOf(id);

        int quantity = product.getQuantity();

        contentValues.put(DatabaseSchemas.ProductTable.Cols.QUANTITY, quantity);
        db.update(DatabaseSchemas.ProductTable.NAME, contentValues, "id = ?", new String[]{idString});
        return true;
    }

    public boolean add(Product product) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        Product productInDB = new Product();

        if (productInDB != null) {
            int quantity = productInDB.getQuantity();
            quantity += product.getQuantity();
            product.setQuantity(quantity);
            update(product);
            return true;
        }

        ContentValues values = new ContentValues();
        values.put(DatabaseSchemas.ProductTable.Cols.UUID, product.getId());
        values.put(DatabaseSchemas.ProductTable.Cols.THUMBNAIL, product.getThumbnail());
        values.put(DatabaseSchemas.ProductTable.Cols.NAME, product.getName());
        values.put(DatabaseSchemas.ProductTable.Cols.CATEGORY, product.getCategory());
        values.put(DatabaseSchemas.ProductTable.Cols.UNIT_PRICE, product.getUnitPrice());
        values.put(DatabaseSchemas.ProductTable.Cols.QUANTITY, product.getQuantity());

        long result = db.insert(DatabaseSchemas.ProductTable.NAME, null, values);

        return true;
    }

    public boolean delete(Long id) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        db.delete(DatabaseSchemas.ProductTable.NAME, "id = ?", new String[]{id + ""});
        return true;
    }
}
