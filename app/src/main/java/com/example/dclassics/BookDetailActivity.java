package com.example.dclassics;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class BookDetailActivity extends AppCompatActivity {

    TextView btnBack, tvBookTitle, tvBookAuthor, tvSynopsis;
    ImageView imgBookCover;
    Button btnBuyNow, btnDigital, btnPhysical;
    EditText etAddress, etPhone;

    String selectedOption = "Physical";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        btnBack = findViewById(R.id.btnBack);
        imgBookCover = findViewById(R.id.imgBookCover);
        tvBookTitle = findViewById(R.id.tvBookTitle);
        tvBookAuthor = findViewById(R.id.tvBookAuthor);
        tvSynopsis = findViewById(R.id.tvSynopsis);

        btnBuyNow = findViewById(R.id.btnBuyNow);
        btnDigital = findViewById(R.id.btnDigital);
        btnPhysical = findViewById(R.id.btnPhysical);

        etAddress = findViewById(R.id.etAddress);
        etPhone = findViewById(R.id.etPhone);

        String intentTitle = getIntent().getStringExtra("book_title");
        String intentAuthor = getIntent().getStringExtra("book_author");
        String intentSynopsis = getIntent().getStringExtra("book_synopsis");
        int bookImage = getIntent().getIntExtra("book_image", R.drawable.laskar_pelangi_2);

        final String bookTitle = intentTitle != null ? intentTitle : "Laskar Pelangi";
        final String bookAuthor = intentAuthor != null ? intentAuthor : "Andrea Hirata";
        final String bookSynopsis = intentSynopsis != null
                ? intentSynopsis
                : "A story of ten children in Belitung who struggle to achieve their dreams through education.";

        tvBookTitle.setText(bookTitle);
        tvBookAuthor.setText(bookAuthor);
        tvSynopsis.setText(bookSynopsis);
        imgBookCover.setImageResource(bookImage);

        // default: Physical kepilih
        updateOptionButton("Physical");

        btnBack.setOnClickListener(v -> finish());

        btnDigital.setOnClickListener(v -> {
            selectedOption = "Digital";
            updateOptionButton("Digital");
            Toast.makeText(this, "Digital selected", Toast.LENGTH_SHORT).show();
        });

        btnPhysical.setOnClickListener(v -> {
            selectedOption = "Physical";
            updateOptionButton("Physical");
            Toast.makeText(this, "Physical selected", Toast.LENGTH_SHORT).show();
        });

        btnBuyNow.setOnClickListener(v -> {
            String address = etAddress.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();

            if (address.isEmpty()) {
                Toast.makeText(this, "Address is required", Toast.LENGTH_SHORT).show();
                return;
            }

            if (phone.isEmpty()) {
                Toast.makeText(this, "Phone number is required", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Buying " + bookTitle + " - " + selectedOption, Toast.LENGTH_SHORT).show();
        });
    }

    private void updateOptionButton(String option) {
        if (option.equals("Digital")) {
            btnDigital.setBackgroundResource(R.drawable.option_brown_bg);
            btnDigital.setTextColor(ContextCompat.getColor(this, android.R.color.white));

            btnPhysical.setBackgroundResource(R.drawable.option_white_bg);
            btnPhysical.setTextColor(ContextCompat.getColor(this, R.color.primary));
        } else {
            btnPhysical.setBackgroundResource(R.drawable.option_brown_bg);
            btnPhysical.setTextColor(ContextCompat.getColor(this, android.R.color.white));

            btnDigital.setBackgroundResource(R.drawable.option_white_bg);
            btnDigital.setTextColor(ContextCompat.getColor(this, R.color.primary));
        }
    }
}