package com.example.myapplication.uyg3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;


public class UrunDetay extends AppCompatActivity {
    SQLiteDatabase database;
    ImageView urunResim;
    TextView textViewUrunadi, textViewUrunFiyat,textViewUrunAdet;
    Button btnResimEkle, btnDegistir,btnSil,btnGeri;

    int id;
    ActivityResultLauncher<Intent> galleryLauncher;
    ActivityResultLauncher<Intent> galleryPermission;
    Bitmap bitmap;

    @Override
    protected void onCreate (@Nullable Bundle savedInsranceState){
        super.onCreate(savedInsranceState);
        setContentView(R.layout.uyg3_urun_detay);

        tanimlamalar();

        Intent i = getIntent();
        id = i.getIntExtra("id",0);
        try{
            database = this.openOrCreateDatabase("Urun",MODE_PRIVATE,null);
            Cursor cursor = databese.rawQuery("SELECT * FROM urunler WHERE id=?",new String[]{String.valueOf(id)});
            int kolonUrunAdi = cursor.getColumnIndex("urunadi");
            int kolonFiyat = cursor.getColumnIndex("fiyat");
            int kolonAdet = cursor.getColumnIndex("adet");
            int kolonResim = cursor.getColumnIndex("resim");
            while (cursor.moveToNext()){
               textViewUrunadi.setText(cursor.getString(kolonUrunAdi));
               textViewUrunFiyat.setText(cursor.getString(kolonFiyat)+"");
               textViewUrunAdet.setText(cursor.getString(kolonAdet));
               byte[] bytes = cursor.getBlob(kolonResim);
               Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
               urunResim.setImageBitmap(bitmap);

        }
            cursor.close();
    }       catch (Exception e) {
            e.printStackTrace();
        }
}}

