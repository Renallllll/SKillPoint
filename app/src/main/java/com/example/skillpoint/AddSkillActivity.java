package com.example.skillpoint;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddSkillActivity extends AppCompatActivity {

    ImageButton btnBack;
    Button btnAddSkill;
    Spinner spinnerCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_skill);

        btnBack = findViewById(R.id.btnBack);
        btnAddSkill = findViewById(R.id.btnAddSkill);
        spinnerCategory = findViewById(R.id.spinnerCategory);

        String[] categories = {
                "Программирование",
                "Базы данных",
                "Администрирование",
                "Сети",
                "Другое"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                categories
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        btnBack.setOnClickListener(v -> finish());

        btnAddSkill.setOnClickListener(v ->
                Toast.makeText(AddSkillActivity.this, "Навык успешно добавлен", Toast.LENGTH_SHORT).show()
        );
    }
}