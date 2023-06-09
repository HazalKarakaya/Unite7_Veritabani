package com.example.myapplication.uyg3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;

public class UrunKayit extends AppCompatActivity {
    SQLiteDatabase database;
    EditText txtUrunAdi, txtUrunFiyat, txtUrunAdet;
    Button btnKaydet;
    int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uyg3_urun_kayit);
        tanimlamalar();
        Intent i = getIntent();
        i.getIntExtra("id", 0);
        String mod = i.getStringExtra("mod");
        database = this.openOrCreateDatabase("Urun", MODE_PRIVATE, null);
        try {
            Cursor cursor = database.rawQuery("SELECT urunadi, fiyat, adet FROM urunler WHERE id=?", new String[]{String.valueOf(id)});
            int kolonUrunAdi = cursor.getColumnIndex("urunadi");
            int kolonUrunFiyat = cursor.getColumnIndex("fiyat");
            int kolonUrunAdet = cursor.getColumnIndex("adet");

            while (cursor.moveToNext()) {
                txtUrunAdi.setText(cursor.getString(kolonUrunAdi));
                txtUrunFiyat.setText(cursor.getString(kolonUrunFiyat) + "");
                txtUrunAdet.setText(cursor.getInt(kolonUrunAdet) + "");
            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        btnKaydet.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                     String SORGU = "INSERT INTO urunler (urunadi, fiyat, adet) VALUES (?,?,?)";
                     SQLiteStatement result = database.compileStatement(SORGU);
                     result.bindString(1, txtUrunAdi.getText().toString());
                     result.bindString(2, txtUrunFiyat.getText().toString());
                     result.bindString(3, txtUrunAdet.getText().toString());
                     result.execute();


                 Intent i = new Intent(UrunKayit. this, Uyg3.class);
                 startActivity(i);
             }
         });

    }
    private void tanimlamalar() {
        txtUrunAdi = findViewById(R.id.txtUrunAdi);
        txtUrunFiyat = findViewById(R.id.txtUrunFiyat);
        txtUrunAdet = findViewById(R.id.txtUrunAdet);
        btnKaydet = findViewById(R.id.buttonKaydet);
    }
}