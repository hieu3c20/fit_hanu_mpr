package com.androicourse.assignment2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androicourse.assignment2.models.Product;
import com.androicourse.assignment2.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> movieList;
    private Context context;


    public interface OnCartClickListener {
        void onCartClick(Product product);
    }

    public ProductAdapter(List<Product> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product movieModelClass = movieList.get(position);


        holder.name.setText(movieModelClass.getName());

        holder.unitPrice.setText(movieModelClass.getUnitPrice() + "");

        Glide.with(context).load(movieModelClass.getThumbnail()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView thumbnail;
        private TextView name, unitPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.tvThumbnail);
            name = itemView.findViewById(R.id.tvTitle);
//            category = itemView.findViewById(R.id.tvCategory);
            unitPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
