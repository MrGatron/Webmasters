package com.example.webmasters.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.webmasters.databinding.ListItemProductBinding;
import com.example.webmasters.models.webstore.Product;
import com.example.webmasters.ui.web_store.ProductActivity;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.function.Consumer;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private final LayoutInflater mInflater;
    private final List<? extends Product> mProducts;
    private final Context mContext;

    private Consumer<Product> mRemoveHandler = null;

    public ProductAdapter(Context context, List<? extends Product> products) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mProducts = products;
    }

    public void setRemoveHandler(Consumer<Product> handler) {
        mRemoveHandler = handler;
    }

    @NonNull
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemProductBinding mBinding = ListItemProductBinding.inflate(mInflater, parent, false);
        return new MyViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product product = mProducts.get(position);
        holder.binding.setHolder(holder);
        holder.binding.setProduct(product);
        if (!product.getImageUrl().isEmpty())
            Picasso.get().load(product.getImageUrl()).into(holder.image);
    }

    public void openProduct(String productId) {
        Intent intent = new Intent(mContext, ProductActivity.class);
        intent.putExtra("productId", productId);
        mContext.startActivity(intent);
    }


    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ListItemProductBinding binding;
        ImageView image;

        public MyViewHolder(ListItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            image = binding.imageProduct;
        }

        public void onSelect() {
            openProduct(binding.getProduct().getId());
        }

        public void onRemove() {
            if (mRemoveHandler == null) return;
            mRemoveHandler.accept(binding.getProduct());
        }
    }
}