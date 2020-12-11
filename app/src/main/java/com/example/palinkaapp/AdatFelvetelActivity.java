package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdatFelvetelActivity extends AppCompatActivity {

    public EditText etAddFozo, etAddGyumolcs, etAddAlkohol;
    private Button btnAdd, btnAddBack;

    DBhelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adat_felvetel);

        init();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adatRogzites();
            }
        });

        btnAddBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(AdatFelvetelActivity.this, MainActivity.class);
                startActivity(back);
                finish();
            }
        });
    }


    private void adatRogzites() {
        String fozo = etAddFozo.getText().toString().trim();
        String gyumolcs = etAddGyumolcs.getText().toString().trim();
        String alkohol = etAddAlkohol.getText().toString().trim();

        if (fozo.isEmpty()) {
            Toast.makeText(this, "Főző nevének megadása kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (gyumolcs.isEmpty()) {
            Toast.makeText(this, "Gyümölcs nevének kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (alkohol.isEmpty()) {
            Toast.makeText(this, "Alkoholtartalom megadása kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }

        int alkoholSzam = Integer.parseInt(alkohol);

        if (alkoholSzam < 1 || alkoholSzam > 99) {
            Toast.makeText(this, "Az alkoholtartalom 1 és 99 közötti szám lehet!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (adatbazis.adatRogzites(fozo, gyumolcs, alkohol)) {
            Toast.makeText(this, "Sikeres rögzítés", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Siktelen rögzítés", Toast.LENGTH_SHORT).show();
        }
    }


    private void init() {
        etAddFozo = findViewById(R.id.etAddFozo);
        etAddGyumolcs = findViewById(R.id.etAddGyumolcs);
        etAddAlkohol = findViewById(R.id.etAddAlkohol);
        btnAdd = findViewById(R.id.btnAdd);
        btnAddBack = findViewById(R.id.btnAddBack);

        adatbazis = new DBhelper(AdatFelvetelActivity.this);
    }
}