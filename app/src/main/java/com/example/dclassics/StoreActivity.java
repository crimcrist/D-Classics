package com.example.dclassics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StoreActivity extends AppCompatActivity {

    LinearLayout navHome, navBooks, navStores, navLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookstore);

        // STORE CARDS
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
                R.drawable.store_1,
                "Greenfield University Library",
                "12 Drive, Campus West"
        );

        setStoreCard(
                storeCard3,
                R.drawable.store_1,
                "Rainbow Reading Center",
                "78 Magnolia Lane, Suite 200"
        );

        setStoreCard(
                storeCard4,
                R.drawable.store_1,
                "Classic Book Hall",
                "9 Heritage Street"
        );

        // BOTTOM NAV
        navHome = findViewById(R.id.navHome);
        navBooks = findViewById(R.id.navBooks);
        navStores = findViewById(R.id.navStores);
        navLogout = findViewById(R.id.navLogout);

        navHome.setOnClickListener(v -> {
            Intent intent = new Intent(StoreActivity.this, MainActivity.class);
            startActivity(intent);
        });

        navBooks.setOnClickListener(v -> {
            Intent intent = new Intent(StoreActivity.this, MainActivity.class);
            startActivity(intent);
        });

        navStores.setOnClickListener(v -> {
            // Sudah berada di halaman StoreActivity, jadi tidak perlu pindah
        });

        navLogout.setOnClickListener(v -> {
            finishAffinity();
        });
    }

    private void setStoreCard(View card, int imageRes, String name, String address) {
        ImageView ivStoreImage = card.findViewById(R.id.ivStoreImage);
        TextView tvStoreName = card.findViewById(R.id.tvStoreName);
        TextView tvStoreAddress = card.findViewById(R.id.tvStoreAddress);

        ivStoreImage.setImageResource(imageRes);
        tvStoreName.setText(name);
        tvStoreAddress.setText(address);
    }
}