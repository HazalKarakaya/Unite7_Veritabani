package com.example.myapplication.uyg3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Uyg3 extends AppCompatActivity {
    SQLiteDatabase database;
    ListView listeUrunler;
    Button btnKaydet;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uyg3);
        listeUrunler = findViewById(R.id.urunListe);
        btnKaydet = findViewById(R.id.btnYeniKayitEkle);
        database = openOrCreateDatabase("urun",MODE_PRIVATE,null);
        guncelle();
    }

    @Override
    protected void onStart() {
        super.onStart();
        guncelle();
    }

    private void guncelle() {
        ArrayList<Urun> verilerList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM urunler",null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String kadi = cursor.getString(cursor.getColumnIndex("urunadi"));
                @SuppressLint("Range") Double fiyat = cursor.getDouble(cursor.getColumnIndex("fiyat"));
                @SuppressLint("Range") Integer adet= cursor.getInt(cursor.getColumnIndex("adet"));
                verilerList.add(new Urun(id,kadi,fiyat,adet));
            } while (cursor.moveToNext());
            UrunAdapter listAdapter = new UrunAdapter(this,verilerList);
            listAdapter.notifyDataSetChanged();
            listeUrunler.setAdapter(listAdapter);
        }
    }

    public void btnYeniKayitEkleClick(View View) {
        Intent i = new Intent(Uyg3.this, UrunDetay.class);
        i.putExtra("islem", true);
        startActivity(i);
    }
}