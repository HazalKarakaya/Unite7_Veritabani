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
    Urun urun;
    ArrayList<Urun> urunler;
    ListView listeUrunler;
    UrunAdapter urunAdapter;
    Button btnKaydet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.uyg3);
        urunler = new ArrayList<>();
        listeUrunler = findViewById(R.id.urunListe);
        urunAdapter = new UrunAdapter(Uyg3.this, urunler);
        btnKaydet = findViewById(R.id.btnYeniKayitEkle);
        listeUrunler.setAdapter(urunAdapter);

        database = this.openOrCreateDatabase( "Urun", MODE_PRIVATE, null );

        String TABLO = "CREATE TABLE IF NOT EXISTS urunler(id INTEGER PRIMARY KEY," +
                "urunadi TEXT,"+
                "fiyat TEXT,"+
                "adet TEXT," +
                "resim BLOB)";
        database.execSQL(TABLO);
        getAllUrunler();

        listeUrunler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(Uyg3. this, UrunDetay.class);
                i.putExtra("id", urunler.get(position).getId());
                System.out.println("######" + urunler.get(position).getId());
                startActivity(i);
            }
        });
    }

    public void btnYeniKayitEkle(View view) {
        Intent i = new Intent(Uyg3.this, UrunKayit.class);
        i.putExtra("mod", "ekle");
        startActivity(i);
    }

    public void getAllUrunler() {

        Cursor cursor = database.rawQuery("SELECT * FROM urunler", null);
        int kolonId = cursor.getColumnIndex("id");
        int kolonUrunAdi = cursor.getColumnIndex("urunadi");
        int kolonFiyat = cursor.getColumnIndex("fiyat");
        int kolonAdet = cursor.getColumnIndex("adet");
        int kolonResim = cursor.getColumnIndex("resim");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(kolonId);
            String urunAdi = cursor.getString(kolonUrunAdi);
            String fiyat = cursor.getString(kolonFiyat);
            String adet = cursor.getString(kolonAdet);
            byte[] resim = cursor.getBlob(kolonResim);

            urun = new Urun(id, urunAdi, fiyat, adet, resim);
            urunler.add(urun);
        }
        cursor.close();
    }

}