package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.uyg3.Uyg3;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDB();
        //insertUrun();
        //updateUrun();
        //deleteUrun();
        //getAllUrunler();

    }

    private void createDB(){
        database = this.openOrCreateDatabase( "Urun", MODE_PRIVATE, null );

        String TABLO = "CREATE TABLE IF NOT EXISTS urunler(id INTEGER PRIMARY KEY," +
                "urunadi TEXT,"+
                "fiyat DOUBLE,"+
                "adet INTEGER)";
        database.execSQL(TABLO);

    }

    public void btnUyg3(View view) {
        Intent intent = new Intent(MainActivity.this, Uyg3.class);
        startActivity(intent);
    }
}