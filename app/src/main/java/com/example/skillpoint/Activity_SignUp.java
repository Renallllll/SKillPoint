package com.example.skillpoint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_SignUp extends AppCompatActivity {

    EditText etNameSignUp, etEmailSignUp, etPasswordSignUp;
    Button btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etNameSignUp = findViewById(R.id.etNameSignUp);
        etEmailSignUp = findViewById(R.id.etEmailSignUp);
        etPasswordSignUp = findViewById(R.id.etPasswordSignUp);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        btnCreateAccount.setOnClickListener(v -> {
            String name = etNameSignUp.getText().toString().trim();
            String email = etEmailSignUp.getText().toString().trim();
            String password = etPasswordSignUp.getText().toString().trim();

            if (name.isEmpty()) {
                etNameSignUp.setError("Введите имя");
                etNameSignUp.requestFocus();
                return;
            }

            if (email.isEmpty()) {
                etEmailSignUp.setError("Введите email");
                etEmailSignUp.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                etPasswordSignUp.setError("Введите пароль");
                etPasswordSignUp.requestFocus();
                return;
            }

            SharedPreferences prefs = getSharedPreferences("skillpoint_prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("is_logged_in", true);
            editor.putString("user_email", email);
            editor.putString("user_name", name);
            editor.apply();

            Intent intent = new Intent(Activity_SignUp.this, MainActivity.class);
            intent.putExtra("user_email", email);
            intent.putExtra("user_name", name);
            startActivity(intent);
            finish();
        });
    }
}