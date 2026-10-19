package com.example.skillpoint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    Button btnSettings;
    FloatingActionButton fabAdd;
    TextView tvEmail, tvUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSettings = findViewById(R.id.btnSettings);
        fabAdd = findViewById(R.id.fabAdd);
        tvEmail = findViewById(R.id.tvEmail);
        tvUserName = findViewById(R.id.tvUserName);

        SharedPreferences prefs = getSharedPreferences("skillpoint_prefs", MODE_PRIVATE);

        String userEmail = prefs.getString("user_email", "email@example.com");
        String userName = prefs.getString("user_name", "Реналь");

        tvEmail.setText(userEmail);
        tvUserName.setText(userName);

        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddSkillActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = getSharedPreferences("skillpoint_prefs", MODE_PRIVATE);
        String userName = prefs.getString("user_name", "Реналь");
        String userEmail = prefs.getString("user_email", "email@example.com");

        tvUserName.setText(userName);
        tvEmail.setText(userEmail);
    }
}