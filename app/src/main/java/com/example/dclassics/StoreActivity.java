package com.example.dclassics;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookstore);

        setupStoreCards();
        setupBottomNav();
        setupNavbarActiveState();
    }

    private void setupStoreCards() {
        View storeCard1 = findViewById(R.id.storeCard1);
        View storeCard2 = findViewById(R.id.storeCard2);
        View storeCard3 = findViewById(R.id.storeCard3);
        View storeCard4 = findViewById(R.id.storeCard4);

        setStoreCard(
                storeCard1,
                R.drawable.store_1,
                "Central City Public Library",
                "45 Avenue, Downtown"
        );

        setStoreCard(
                storeCard2,
                R.drawable.store_2,
                "Greenfield University Library",
                "12 Drive, Campus West"
        );

        setStoreCard(
                storeCard3,
                R.drawable.store_3,
                "Rainbow Reading Center",
                "78 Magnolia Lane, Suite 200"
        );

        setStoreCard(
                storeCard4,
                R.drawable.store_4,
                "Classic Book Hall",
                "9 Heritage Street"
        );
    }

    private void setStoreCard(View card, int imageRes, String name, String address) {
        ImageView ivStoreImage = card.findViewById(R.id.ivStoreImage);
        TextView tvStoreName = card.findViewById(R.id.tvStoreName);
        TextView tvStoreAddress = card.findViewById(R.id.tvStoreAddress);

        ivStoreImage.setImageResource(imageRes);
        tvStoreName.setText(name);
        tvStoreAddress.setText(address);
    }

    private void setupBottomNav() {
        View navHome = findViewById(R.id.navHome);
        View navBooks = findViewById(R.id.navBooks);
        View navStores = findViewById(R.id.navStores);
        View navLogout = findViewById(R.id.navLogout);

        navHome.setOnClickListener(v -> {
            startActivity(new Intent(StoreActivity.this, MainActivity.class));
            finish();
        });

        navBooks.setOnClickListener(v -> {
            startActivity(new Intent(StoreActivity.this, AllBooksActivity.class));
            finish();
        });

        navStores.setOnClickListener(v -> {
            // sudah di halaman Stores
        });

        navLogout.setOnClickListener(v -> {
            startActivity(new Intent(StoreActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void setupNavbarActiveState() {
        ImageView icNavStores = findViewById(R.id.icNavStores);
        TextView tvNavStores = findViewById(R.id.tvNavStores);

        icNavStores.setImageResource(R.drawable.ic_store_gradient);

        tvNavStores.post(() -> {
            LinearGradient gradient = new LinearGradient(
                    0,
                    0,
                    tvNavStores.getWidth(),
                    0,
                    new int[]{
                            0xFFEBCB70,
                            0xFFD8A93B
                    },
                    null,
                    Shader.TileMode.CLAMP
            );

            tvNavStores.getPaint().setShader(gradient);
            tvNavStores.invalidate();
        });
    }
}