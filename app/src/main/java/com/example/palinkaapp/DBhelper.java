package com.example.palinkaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "palinka.db";
    public static final int DB_VERSION = 1;

    public static final String PALINKA_TABLE = "palinka";

    public static final String COL_ID       = "id";
    public static final String COL_FOZO     = "fozo";
    public static final String COL_GYUMOLCS = "gyumolcs";
    public static final String COL_ALKOHOL  = "alkohol";

    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql  = "CREATE TABLE IF NOT EXISTS "+PALINKA_TABLE+" (" +
                COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_FOZO+" VARCHAR(255) NOT NULL, " +
                COL_GYUMOLCS+" VARCHAR(255) NOT NULL, " +
                COL_ALKOHOL+" INTEGER NOT NULL " +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + PALINKA_TABLE;
        db.execSQL(sql);
        onCreate(db);
    }

    public boolean adatRogzites(String fozo, String gyumolcs, String alkohol) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FOZO, fozo);
        values.put(COL_GYUMOLCS, gyumolcs);
        values.put(COL_ALKOHOL, alkohol);
        return db.insert(PALINKA_TABLE, null, values) != -1;
    }

    public Cursor adatLekerdezes() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(PALINKA_TABLE, new String[]{COL_ID, COL_FOZO, COL_GYUMOLCS, COL_ALKOHOL}, null, null,
                null, null, null);
    }

    public boolean adatKereses() {
        return false;
    }

}
