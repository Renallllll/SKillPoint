package com.example.skillpoint;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SqlActivity extends AppCompatActivity {

    ImageButton btnBack;
    ImageButton check1, check2, check3, check4, check5, check6, check7, check8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);

        btnBack = findViewById(R.id.btnBack);

        check1 = findViewById(R.id.check1);
        check2 = findViewById(R.id.check2);
        check3 = findViewById(R.id.check3);
        check4 = findViewById(R.id.check4);
        check5 = findViewById(R.id.check5);
        check6 = findViewById(R.id.check6);
        check7 = findViewById(R.id.check7);
        check8 = findViewById(R.id.check8);

        btnBack.setOnClickListener(v -> finish());

        setupToggle(check1, true);
        setupToggle(check2, false);
        setupToggle(check3, true);
        setupToggle(check4, false);
        setupToggle(check5, true);
        setupToggle(check6, false);
        setupToggle(check7, true);
        setupToggle(check8, false);
    }

    private void setupToggle(ImageButton button, boolean isGreen) {
        button.setTag(isGreen);
        button.setImageResource(isGreen ? R.drawable.ic_check_green_background : R.drawable.ic_check_red_background);

        button.setOnClickListener(v -> {
            boolean current = (boolean) button.getTag();
            boolean next = !current;
            button.setTag(next);
            button.setImageResource(next ? R.drawable.ic_check_green_background : R.drawable.ic_check_red_background);
        });
    }
}