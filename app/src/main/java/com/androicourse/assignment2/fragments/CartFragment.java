package com.androicourse.assignment2.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.androicourse.assignment2.R;
import com.androicourse.assignment2.adapters.ProductAdapter;
import com.androicourse.assignment2.db.DatabaseHelper;
import com.androicourse.assignment2.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends AppCompatActivity {
    private List<Product> productList = new ArrayList<>();
    private RecyclerView recyclerView  = findViewById(R.id.recycle_view_cart);;
    private ProductAdapter productAdapter;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_cart_view);

    }




//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cart_view);
//
//        productAdapter = new ProductAdapter(productList, this);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        recyclerView.setAdapter(productAdapter);
//
//        // Get the products from the SQLite database and add them to the product list
//        databaseHelper = new DatabaseHelper(this);
//        productList.addAll(databaseHelper.getAllProducts());
//        productAdapter.notifyDataSetChanged();
//    }
}