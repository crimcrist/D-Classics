package com.example.dclassics;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(0, 0, 0, bars.bottom);
            return insets;
        });

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        tvError = findViewById(R.id.tv_error);

        findViewById(R.id.btn_login).setOnClickListener(v -> validateAndLogin());

        findViewById(R.id.btn_guest).setOnClickListener(v -> {
            Session.username = "GUEST";
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        TextView signupLink = findViewById(R.id.link_signup);
        boldUnderline(signupLink);
        signupLink.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
            finish();
        });

        TextView forgotLink = findViewById(R.id.link_forgot);
        boldUnderline(forgotLink);
        forgotLink.setOnClickListener(v -> showForgotDialog());
    }

    private void showForgotDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_forgot);

        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            int width = Math.round(getResources().getDisplayMetrics().density * 340);
            window.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
        }

        EditText etEmail = dialog.findViewById(R.id.et_forgot_email);
        TextView error = dialog.findViewById(R.id.tv_forgot_error);
        View form = dialog.findViewById(R.id.group_forgot_form);
        View success = dialog.findViewById(R.id.group_forgot_success);

        dialog.findViewById(R.id.btn_close_forgot).setOnClickListener(v -> dialog.dismiss());
        dialog.findViewById(R.id.btn_continue_forgot).setOnClickListener(v -> dialog.dismiss());

        dialog.findViewById(R.id.btn_submit_forgot).setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            if (email.isEmpty()) {
                error.setText(R.string.err_email_empty);
                error.setVisibility(View.VISIBLE);
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || !email.endsWith(".com")) {
                error.setText(R.string.err_email);
                error.setVisibility(View.VISIBLE);
            } else {
                error.setVisibility(View.GONE);
                form.setVisibility(View.GONE);
                success.setVisibility(View.VISIBLE);
            }
        });

        dialog.show();
    }

    private void validateAndLogin() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();

        String error = null;
        if (username.isEmpty()) {
            error = getString(R.string.err_username);
        } else if (password.isEmpty()) {
            error = getString(R.string.err_password_empty);
        } else if (!password.matches("(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]+")) {
            error = getString(R.string.err_password_alnum);
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

    private void boldUnderline(TextView tv) {
        tv.setPaintFlags(tv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
    }
}
