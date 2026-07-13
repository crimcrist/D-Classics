package com.example.dclassics;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private View tvMoreBooks;
    private View tvMoreStores;

    private ViewPager2 storeCarousel;
    private View dotStore1;
    private View dotStore2;
    private View dotStore3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    0
            );
            return insets;
        });

        setupViews();
        setupWelcomeText();
        setupMoreButtons();
        setupCarousel();
        setupNavbarActiveState();
        setupBottomNav();
    }

    private void setupViews() {
        tvWelcome = findViewById(R.id.tv_welcome);
        tvMoreBooks = findViewById(R.id.tvMoreBooks);
        tvMoreStores = findViewById(R.id.tvMoreStores);

        storeCarousel = findViewById(R.id.storeCarousel);

        dotStore1 = findViewById(R.id.dotStore1);
        dotStore2 = findViewById(R.id.dotStore2);
        dotStore3 = findViewById(R.id.dotStore3);
    }

    private void setupWelcomeText() {
        if (Session.username != null && !Session.username.isEmpty()) {
            tvWelcome.setText(Session.username);
        } else {
            tvWelcome.setText("USER");
        }
    }

    private void setupMoreButtons() {
        tvMoreBooks.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AllBooksActivity.class));
        });

        tvMoreStores.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, StoreActivity.class));
        });
    }

    private void setupCarousel() {
        int[] storeImages = {
                R.drawable.store_1,
                R.drawable.store_2,
                R.drawable.store_3
        };

        StoreCarouselAdapter adapter = new StoreCarouselAdapter(storeImages);
        storeCarousel.setAdapter(adapter);

        updateStoreDots(0);

        dotStore1.setOnClickListener(v -> storeCarousel.setCurrentItem(0, true));
        dotStore2.setOnClickListener(v -> storeCarousel.setCurrentItem(1, true));
        dotStore3.setOnClickListener(v -> storeCarousel.setCurrentItem(2, true));

        View btnStorePrev = findViewById(R.id.btnStorePrev);
        View btnStoreNext = findViewById(R.id.btnStoreNext);

        btnStorePrev.setOnClickListener(v -> {
            int current = storeCarousel.getCurrentItem();

            if (current == 0) {
                storeCarousel.setCurrentItem(storeImages.length - 1, true);
            } else {
                storeCarousel.setCurrentItem(current - 1, true);
            }
        });

        btnStoreNext.setOnClickListener(v -> {
            int current = storeCarousel.getCurrentItem();

            if (current == storeImages.length - 1) {
                storeCarousel.setCurrentItem(0, true);
            } else {
                storeCarousel.setCurrentItem(current + 1, true);
            }
        });

        storeCarousel.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateStoreDots(position);
            }
        });
    }

    private void updateStoreDots(int position) {
        setDotState(dotStore1, position == 0);
        setDotState(dotStore2, position == 1);
        setDotState(dotStore3, position == 2);
    }

    private void setDotState(View dot, boolean isActive) {
        ViewGroup.LayoutParams params = dot.getLayoutParams();

        if (isActive) {
            params.width = dpToPx(34);
            params.height = dpToPx(10);
            dot.setBackgroundResource(R.drawable.bg_dot_active_pill);
        } else {
            params.width = dpToPx(10);
            params.height = dpToPx(10);
            dot.setBackgroundResource(R.drawable.bg_dot_inactive);
        }

        dot.setLayoutParams(params);
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }

    private void setupNavbarActiveState() {
        ImageView icNavHome = findViewById(R.id.icNavHome);
        TextView tvNavHome = findViewById(R.id.tvNavHome);

        icNavHome.setImageResource(R.drawable.ic_home_gradient);

        tvNavHome.post(() -> {
            LinearGradient gradient = new LinearGradient(
                    0,
                    0,
                    tvNavHome.getWidth(),
                    0,
                    new int[]{
                            0xFFEBCB70,
                            0xFFD8A93B,
                            0xFFFFE3A3
                    },
                    null,
                    Shader.TileMode.CLAMP
            );

            tvNavHome.getPaint().setShader(gradient);
            tvNavHome.invalidate();
        });
    }


    // NAVBAR BOTTOM
    private void setupBottomNav() {
        View navHome = findViewById(R.id.navHome);
        View navBooks = findViewById(R.id.navBooks);
        View navStores = findViewById(R.id.navStores);
        View navLogout = findViewById(R.id.navLogout);

        navHome.setOnClickListener(v -> {
            // sudah di halaman Home
        });

        navBooks.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AllBooksActivity.class));
            finish();
        });

        navStores.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, StoreActivity.class));
            finish();
        });

        navLogout.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });
    }
}