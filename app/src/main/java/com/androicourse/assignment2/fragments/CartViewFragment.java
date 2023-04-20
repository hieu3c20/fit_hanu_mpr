package com.androicourse.assignment2.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androicourse.assignment2.CurrencyFormatter;
import com.androicourse.assignment2.R;
import com.androicourse.assignment2.adapters.CartAdapter;
import com.androicourse.assignment2.db.ProductManager;
import com.androicourse.assignment2.models.Product;

import java.util.List;


public class CartViewFragment extends AppCompatActivity {

    private RecyclerView cartRv;
    private CartAdapter cartAdapter;
    private TextView totalPrice;
    private List<Product> carts;
    private ProductManager pManager;

    public CartViewFragment(List<Product> carts, ProductManager pManager){
        this.carts = carts;
        this.pManager = pManager;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cart_view_fragment, container, false);

        cartRv = view.findViewById(R.id.recyclerView2);
        TextView total = view.findViewById(R.id.tvTotalPrice);
        totalPrice = view.findViewById(R.id.tvTotalPrice1);

        totalPrice.setText(CurrencyFormatter.format((long) calculateTotalPrice()));


        cartAdapter = new CartAdapter(carts, pManager);
        cartRv.setAdapter(cartAdapter);

        cartRv.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));

        return view;
    }

    private int calculateTotalPrice(){
        int totalPrice = 0;

        for (Product product: carts){
            totalPrice += product.getUnitPrice() * product.getQuantity();
        }

        return totalPrice;
    }

    @Override
    public void onResume(){
        super.onResume();
        cartAdapter.onItemClick(new RecyclerViewItemClick() {
            @Override
            public void onItemClick(int position, View v) {
                totalPrice.setText(CurrencyFormatter.format((long) calculateTotalPrice()));
            }
        });
    }

    public void refresh(){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.detach(this);
        fragmentTransaction.attach(this);
        fragmentTransaction.commit();
    }
}