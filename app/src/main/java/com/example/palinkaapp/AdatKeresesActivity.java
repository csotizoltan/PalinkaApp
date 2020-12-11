package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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