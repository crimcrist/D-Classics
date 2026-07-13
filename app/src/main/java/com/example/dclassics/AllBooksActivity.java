package com.example.dclassics;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.widget.ImageView;

public class AllBooksActivity extends AppCompatActivity {

    private CardView btnAll, btnFiction, btnNonFiction;
    private TextView tvAll, tvFiction, tvNonFiction;
    private EditText etSearchBook;
    private RecyclerView rvBooks;

    private BookAdapter bookAdapter;
    private final List<AllBookItem> allBooks = new ArrayList<>();

    private String selectedCategory = "All";
    private String searchText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        setupViews();
        setupBooks();
        setupRecyclerView();
        setupFilters();
        setupSearch();
        setupBottomNav();

        applyFilter();
        setActiveFilter(btnAll);
        setupNavbarActiveState();

    }

    private void setupViews() {
        btnAll = findViewById(R.id.btnAll);
        btnFiction = findViewById(R.id.btnFiction);
        btnNonFiction = findViewById(R.id.btnNonFiction);

        tvAll = findViewById(R.id.tvAll);
        tvFiction = findViewById(R.id.tvFiction);
        tvNonFiction = findViewById(R.id.tvNonFiction);

        etSearchBook = findViewById(R.id.etSearchBook);
        rvBooks = findViewById(R.id.rvBooks);
    }

    private void setupBooks() {
        allBooks.add(new AllBookItem(
                "Atomic Habits",
                "James Clear",
                "A practical guide to building good habits and breaking bad ones for lasting success.",
                R.drawable.book_atomic_habit,
                AllBookItem.NON_FICTION
        ));

        allBooks.add(new AllBookItem(
                "Laskar Pelangi",
                "Andrea Hirata",
                "A story of ten children in Belitung who struggle to achieve their dreams through education.",
                R.drawable.book_laskar_pelangi,
                AllBookItem.FICTION
        ));

        allBooks.add(new AllBookItem(
                "Dilan 1990",
                "Pidi Baiq",
                "A heartwarming teenage romance between Dilan and Milea set in the 1990s.",
                R.drawable.book_dilan,
                AllBookItem.FICTION
        ));

        allBooks.add(new AllBookItem(
                "The Hobbit",
                "J.R.R. Tolkien",
                "A young hobbit embarks on an epic journey filled with dragons, treasure, and adventure.",
                R.drawable.book_hobbit,
                AllBookItem.FICTION
        ));

        allBooks.add(new AllBookItem(
                "Rich Dad Poor Dad",
                "Robert T. Kiyosaki",
                "Lessons on money, investing, and achieving financial independence.",
                R.drawable.book_rich_dad,
                AllBookItem.NON_FICTION
        ));

        allBooks.add(new AllBookItem(
                "Filosofi Teras",
                "Henry Manampiring",
                "An introduction to Stoic philosophy and how it can help in everyday life.",
                R.drawable.book_filosofi_teras,
                AllBookItem.NON_FICTION
        ));

        allBooks.add(new AllBookItem(
                "Sapiens",
                "Yuval Noah Harari",
                "An exploration of the history and evolution of humankind.",
                R.drawable.book_sapiens,
                AllBookItem.NON_FICTION
        ));

        allBooks.add(new AllBookItem(
                "Think and Grow Rich",
                "Napoleon Hill",
                "A classic book that reveals principles for achieving success and wealth.",
                R.drawable.book_think_grow,
                AllBookItem.NON_FICTION
        ));

        allBooks.add(new AllBookItem(
                "Harry Potter and the Philosopher's Stone",
                "J.K. Rowling",
                "A young boy discovers he is a wizard and begins his magical journey at Hogwarts.",
                R.drawable.book_harry_potter,
                AllBookItem.FICTION
        ));

        allBooks.add(new AllBookItem(
                "The Psychology of Money",
                "Morgan Housel",
                "An insightful book that explains how emotions and behavior influence financial decisions.",
                R.drawable.book_psychology_money,
                AllBookItem.NON_FICTION
        ));
    }

    private void setupRecyclerView() {
        bookAdapter = new BookAdapter(new ArrayList<>());

        rvBooks.setLayoutManager(new LinearLayoutManager(this));
        rvBooks.setAdapter(bookAdapter);
    }

    private void setupFilters() {
        btnAll.setOnClickListener(v -> {
            selectedCategory = "All";
            setActiveFilter(btnAll);
            applyFilter();
        });

        btnFiction.setOnClickListener(v -> {
            selectedCategory = AllBookItem.FICTION;
            setActiveFilter(btnFiction);
            applyFilter();
        });

        btnNonFiction.setOnClickListener(v -> {
            selectedCategory = AllBookItem.NON_FICTION;
            setActiveFilter(btnNonFiction);
            applyFilter();
        });
    }

    private void setupSearch() {
        etSearchBook.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchText = s.toString().toLowerCase().trim();
                applyFilter();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void applyFilter() {
        List<AllBookItem> filteredBooks = new ArrayList<>();

        for (AllBookItem book : allBooks) {
            boolean matchesCategory = selectedCategory.equals("All")
                    || book.getCategory().equals(selectedCategory);

            boolean matchesSearch = searchText.isEmpty()
                    || book.getTitle().toLowerCase().contains(searchText)
                    || book.getAuthor().toLowerCase().contains(searchText)
                    || book.getDescription().toLowerCase().contains(searchText);

            if (matchesCategory && matchesSearch) {
                filteredBooks.add(book);
            }
        }

        bookAdapter.setBooks(filteredBooks);
    }

    private void setActiveFilter(CardView activeButton) {
        tvAll.setBackgroundResource(R.drawable.bg_filter_inactive_stroke);
        tvFiction.setBackgroundResource(R.drawable.bg_filter_inactive_stroke);
        tvNonFiction.setBackgroundResource(R.drawable.bg_filter_inactive_stroke);

        tvAll.setTextColor(0xFF5A382C);
        tvFiction.setTextColor(0xFF5A382C);
        tvNonFiction.setTextColor(0xFF5A382C);

        if (activeButton == btnAll) {
            tvAll.setBackgroundResource(R.drawable.bg_filter_active_stroke);
            tvAll.setTextColor(0xFFFFFFFF);
        } else if (activeButton == btnFiction) {
            tvFiction.setBackgroundResource(R.drawable.bg_filter_active_stroke);
            tvFiction.setTextColor(0xFFFFFFFF);
        } else if (activeButton == btnNonFiction) {
            tvNonFiction.setBackgroundResource(R.drawable.bg_filter_active_stroke);
            tvNonFiction.setTextColor(0xFFFFFFFF);
        }
    }

    // NAVBAR BOTTOM
    private void setupBottomNav() {
        View navHome = findViewById(R.id.navHome);
        View navBooks = findViewById(R.id.navBooks);
        View navStores = findViewById(R.id.navStores);
        View navLogout = findViewById(R.id.navLogout);

        navHome.setOnClickListener(v -> {
            startActivity(new Intent(AllBooksActivity.this, MainActivity.class));
            finish();
        });

        navBooks.setOnClickListener(v -> {
            // sudah di halaman All Books, jadi tidak usah pindah
        });

        navStores.setOnClickListener(v -> {
            startActivity(new Intent(AllBooksActivity.this, StoreActivity.class));
            finish();
        });

        navLogout.setOnClickListener(v -> {
            startActivity(new Intent(AllBooksActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void setupNavbarActiveState() {
        ImageView icNavBooks = findViewById(R.id.icNavBooks);
        TextView tvNavBooks = findViewById(R.id.tvNavBooks);

        icNavBooks.setImageResource(R.drawable.ic_book_gradient);

        tvNavBooks.post(() -> {
            LinearGradient gradient = new LinearGradient(
                    0,
                    0,
                    tvNavBooks.getWidth(),
                    0,
                    new int[]{
                            0xFFEBCB70,
                            0xFFD8A93B
                    },
                    null,
                    Shader.TileMode.CLAMP
            );

            tvNavBooks.getPaint().setShader(gradient);
            tvNavBooks.invalidate();
        });
    }
}