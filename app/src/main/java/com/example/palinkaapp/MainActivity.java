package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnMainAdd, btnMainFind, btnMainList;
    private TextView tvList;

    DBhelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        listeners();
    }

    private void listeners() {
        btnMainAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(MainActivity.this, AdatFelvetelActivity.class);
                startActivity(add);
                finish();
            }
        });

        btnMainFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent find = new Intent(MainActivity.this, AdatKeresesActivity.class);
                startActivity(find);
                finish();
            }
        });

        btnMainList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataList();
            }
        });
    }


    private void dataList() {
        Cursor adatok = adatbazis.adatLekerdezes();

        if (adatok == null) {
            Toast.makeText(this, "Hiba történt a lekérdezés során!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (adatok.getCount() == 0) {
            Toast.makeText(this, "Még nincs felvéve adat!", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder builder = new StringBuilder();
        while (adatok.moveToNext()) {
            builder.append("ID: ").append(adatok.getInt(0)).append("\n");
            builder.append("Főző: ").append(adatok.getString(1)).append("\n");
            builder.append("Gyümölcs: ").append(adatok.getString(2)).append("\n");
            builder.append("Alkoholtartalom: ").append(adatok.getInt(3)).append(" %").append("\n\n");

            tvList.setText(builder.toString());
            Toast.makeText(this, "Sikeres adatlekérdezés", Toast.LENGTH_SHORT).show();
        }
    }


    private void init() {
        btnMainAdd = findViewById(R.id.btnMainAdd);
        btnMainFind = findViewById(R.id.btnMainFind);
        btnMainList = findViewById(R.id.btnMainList);
        tvList = findViewById(R.id.tvList);

        tvList.setMovementMethod(new ScrollingMovementMethod());

        adatbazis = new DBhelper(MainActivity.this);
    }
}