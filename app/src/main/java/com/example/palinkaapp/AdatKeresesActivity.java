package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdatKeresesActivity extends AppCompatActivity {

    public EditText etFindFozo, etFindGyumolcs;
    public Button btnFind, btnFindBack;
    public TextView tvFindList;

    DBhelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adat_kereses);

        init();

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adatKereses();
            }
        });
        btnFindBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(AdatKeresesActivity.this, MainActivity.class);
                startActivity(back);
                finish();
            }
        });
    }


    private void adatKereses() {
        String fozo = etFindFozo.getText().toString().trim();
        String gyumolcs = etFindGyumolcs.getText().toString().trim();

        if (fozo.isEmpty()) {
            Toast.makeText(this, "Főző nevének megadása kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (gyumolcs.isEmpty()) {
            Toast.makeText(this, "Gyümölcs nevének kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }


        Cursor adatok = adatbazis.adatKereses(fozo, gyumolcs);

        StringBuilder builder = new StringBuilder();
        while (adatok.moveToNext()) {
            builder.append("Alkoholtartalom: ").append(adatok.getInt(3)).append(" %\n");

            tvFindList.setText(builder.toString());
            Toast.makeText(this, "Sikeres keresés", Toast.LENGTH_SHORT).show();
        }

        if (adatok == null) {
            Toast.makeText(this, "Hiba a keresés során", Toast.LENGTH_SHORT).show();
        }

        if (adatok.getCount() == 0) {
            Toast.makeText(this, "A megadott adatokkal nem található pálinka!", Toast.LENGTH_SHORT).show();
        }
    }


    private void init() {
        etFindFozo = findViewById(R.id.etFindFozo);
        etFindGyumolcs = findViewById(R.id.etFindGyumolcs);
        btnFind = findViewById(R.id.btnFind);
        btnFindBack = findViewById(R.id.btnFindBack);
        tvFindList = findViewById(R.id.tvFindList);

        adatbazis = new DBhelper(AdatKeresesActivity.this);
    }
}