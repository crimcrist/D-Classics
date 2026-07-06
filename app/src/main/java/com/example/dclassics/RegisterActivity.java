package com.example.dclassics;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.LeadingMarginSpan;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirm;
    private CheckBox cbTerms;
    private TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(0, 0, 0, bars.bottom);
            return insets;
        });

        etEmail = findViewById(R.id.et_email);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etConfirm = findViewById(R.id.et_confirm);
        cbTerms = findViewById(R.id.check_terms);
        tvError = findViewById(R.id.tv_error);

        TextView loginLink = findViewById(R.id.link_login);
        underline(loginLink);
        loginLink.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        TextView termsLink = findViewById(R.id.link_terms);
        underline(termsLink);
        termsLink.setOnClickListener(v -> showTermsDialog());

        findViewById(R.id.btn_signup).setOnClickListener(v -> validateAndRegister());
    }

    private void validateAndRegister() {
        String email = etEmail.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();
        String confirm = etConfirm.getText().toString();

        String error = null;
        if (email.isEmpty()) {
            error = getString(R.string.err_email_empty);
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || !email.endsWith(".com")) {
            error = getString(R.string.err_email);
        } else if (username.isEmpty()) {
            error = getString(R.string.err_username);
        } else if (password.isEmpty()) {
            error = getString(R.string.err_password_empty);
        } else if (!password.matches("(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]+")) {
            error = getString(R.string.err_password_alnum);
        } else if (!confirm.equals(password)) {
            error = getString(R.string.err_confirm);
        } else if (!cbTerms.isChecked()) {
            error = getString(R.string.err_terms);
        }

        if (error != null) {
            tvError.setText(error);
            tvError.setVisibility(View.VISIBLE);
            return;
        }

        // validation passed
        tvError.setVisibility(View.GONE);
        Session.username = username;
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void underline(TextView tv) {
        tv.setPaintFlags(tv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    private void showTermsDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_terms);

        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            int width = Math.round(getResources().getDisplayMetrics().density * 340);
            window.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
        }

        TextView body = dialog.findViewById(R.id.terms_body_text);
        applyHangingBullets(body);

        dialog.findViewById(R.id.btn_close_terms).setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    // formatting bullet points
    private void applyHangingBullets(TextView body) {
        String[] lines = getString(R.string.terms_body).split("\n");
        int indent = Math.round(body.getPaint().measureText("•  "));

        SpannableStringBuilder sb = new SpannableStringBuilder();
        for (int i = 0; i < lines.length; i++) {
            int start = sb.length();
            sb.append(lines[i]);
            if (i < lines.length - 1) {
                sb.append("\n");
            }
            if (lines[i].startsWith("•")) {
                sb.setSpan(new LeadingMarginSpan.Standard(0, indent),
                        start, sb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        body.setText(sb);
    }
}
