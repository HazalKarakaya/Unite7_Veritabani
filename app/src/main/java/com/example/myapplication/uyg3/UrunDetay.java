package com.example.myapplication.uyg3;


import android.content.ContentValues;
import android.content.Intent;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication.R;

import java.io.ByteArrayOutputStream;

public class UrunDetay extends AppCompatActivity {
    SQLiteDatabase database;

    ImageView urunResim;
    TextView textViewUrunadi,textViewUrunFiyat,textViewUrunAdet;
    Button btnResimEkle,btnDegistir,btnSil,btnGeri;

    int id;
    ActivityResultLauncher<Intent>galleryLauncher;
    ActivityResultLauncher<String>galleryPermission;
    Bitmap bitmap;
    private Object UrunDetay;

    Boolean islem = false;
    Integer islemid;

    private void init() {
        btnDegistir = findViewById(R.id.button4);
        textViewUrunadi = findViewById(R.id.uAdiTxt);
        textViewUrunFiyat = findViewById(R.id.uFiyatTxt);
        textViewUrunAdet = findViewById(R.id.uAdetTxt);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uyg3_urun_detay);
        init();
        Intent i = getIntent();
        database = openOrCreateDatabase("urun",MODE_PRIVATE,null);
        islem = i.getBooleanExtra("islem",false);
        if (islem)
            btnDegistir.setText("Kayıt Ekle");
        else {
            textViewUrunadi.setText(i.getStringExtra("kadi"));
            textViewUrunFiyat.setText(String.valueOf(i.getDoubleExtra("fiyat",0)));
            textViewUrunAdet.setText(String.valueOf(i.getLongExtra("adet",0)));
            islemid = i.getIntExtra("islemid",0);
            btnDegistir.setText("Güncelle");
        }
    }

    public void btnIslem(View view) {
        String uAdi = textViewUrunadi.getText().toString();
        Double uFiyat = Double.parseDouble(textViewUrunFiyat.getText().toString());
        Integer uAdet = Integer.parseInt(textViewUrunAdet.getText().toString());
        if (islem) {
            SQLiteStatement sqLiteStatement = database.compileStatement("INSERT INTO urunler (urunadi,fiyat,adet) VALUES (?,?,?)");
            sqLiteStatement.bindString(1,uAdi);
            sqLiteStatement.bindDouble(2,uFiyat);
            sqLiteStatement.bindLong(3,uAdet);
            sqLiteStatement.execute();
            Toast.makeText(this, "Veri eklendi.", Toast.LENGTH_SHORT).show();
        } else {
            SQLiteStatement sqLiteStatement = database.compileStatement("UPDATE urunler SET urunadi = ?, fiyat = ?, adet = ? WHERE id = ?");
            sqLiteStatement.bindString(1,uAdi);
            sqLiteStatement.bindDouble(2,uFiyat);
            sqLiteStatement.bindLong(3,uAdet);
            sqLiteStatement.bindLong(4,islemid);
            sqLiteStatement.execute();
            Toast.makeText(this, "Veri Güncellendi.", Toast.LENGTH_SHORT).show();
        }
    }
}