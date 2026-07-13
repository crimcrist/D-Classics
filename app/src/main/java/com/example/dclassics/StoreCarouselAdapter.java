package com.example.dclassics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StoreCarouselAdapter extends RecyclerView.Adapter<StoreCarouselAdapter.StoreViewHolder> {

    private final int[] storeImages;

    public StoreCarouselAdapter(int[] storeImages) {
        this.storeImages = storeImages;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_store_carousel, parent, false);
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        holder.imgStoreSlide.setImageResource(storeImages[position]);
    }

    @Override
    public int getItemCount() {
        return storeImages.length;
    }

    static class StoreViewHolder extends RecyclerView.ViewHolder {
        ImageView imgStoreSlide;

        StoreViewHolder(@NonNull View itemView) {
            super(itemView);
            imgStoreSlide = itemView.findViewById(R.id.imgStoreSlide);
        }
    }
}