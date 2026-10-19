package com.example.skillpoint;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    ImageButton btnBack;
    EditText etUserName, etUserEmail, etPassword, etConfirmPassword;
    Button btnSaveProfile;
    TextView tvCurrentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        btnBack = findViewById(R.id.btnBack);
        etUserName = findViewById(R.id.etUserName);
        etUserEmail = findViewById(R.id.etUserEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSaveProfile = findViewById(R.id.btnSaveProfile);
        tvCurrentName = findViewById(R.id.tvCurrentName);

        SharedPreferences prefs = getSharedPreferences("skillpoint_prefs", MODE_PRIVATE);

        String userName = prefs.getString("user_name", "Реналь");
        String userEmail = prefs.getString("user_email", "");

        tvCurrentName.setText(userName);
        etUserName.setText(userName);
        etUserEmail.setText(userEmail);

        btnBack.setOnClickListener(v -> finish());

        btnSaveProfile.setOnClickListener(v -> {
            String newName = etUserName.getText().toString().trim();
            String newEmail = etUserEmail.getText().toString().trim();
            String newPassword = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            if (newName.isEmpty()) {
                etUserName.setError("Введите имя");
                etUserName.requestFocus();
                return;
            }

            if (newEmail.isEmpty()) {
                etUserEmail.setError("Введите Email");
                etUserEmail.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()) {
                etUserEmail.setError("Введите корректный Email");
                etUserEmail.requestFocus();
                return;
            }

            if (!newPassword.isEmpty() || !confirmPassword.isEmpty()) {
                if (newPassword.length() < 8) {
                    etPassword.setError("Минимум 8 символов");
                    etPassword.requestFocus();
                    return;
                }

                if (!newPassword.equals(confirmPassword)) {
                    etConfirmPassword.setError("Пароли не совпадают");
                    etConfirmPassword.requestFocus();
                    return;
                }
            }

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("user_name", newName);
            editor.putString("user_email", newEmail);

            if (!newPassword.isEmpty()) {
                editor.putString("user_password", newPassword);
            }

            editor.apply();

            Toast.makeText(EditProfileActivity.this, "Профиль сохранён", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}