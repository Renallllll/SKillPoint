package com.example.skillpoint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_LogIn extends AppCompatActivity {

    EditText etEmailLogin, etPasswordLogin;
    Button btnEnterLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        SharedPreferences prefs = getSharedPreferences("skillpoint_prefs", MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("is_logged_in", false);

        if (isLoggedIn) {
            Intent intent = new Intent(Activity_LogIn.this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        etEmailLogin = findViewById(R.id.etEmailLogin);
        etPasswordLogin = findViewById(R.id.etPasswordLogin);
        btnEnterLogin = findViewById(R.id.btnEnterLogin);

        btnEnterLogin.setOnClickListener(v -> {
            String email = etEmailLogin.getText().toString().trim();
            String password = etPasswordLogin.getText().toString().trim();

            if (email.isEmpty()) {
                etEmailLogin.setError("Введите email");
                etEmailLogin.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                etPasswordLogin.setError("Введите пароль");
                etPasswordLogin.requestFocus();
                return;
            }

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("is_logged_in", true);
            editor.putString("user_email", email);
            editor.apply();

            Intent intent = new Intent(Activity_LogIn.this, MainActivity.class);
            intent.putExtra("user_email", email);
            startActivity(intent);
            finish();
        });
    }
}