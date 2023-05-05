package com.example.myapplication.uyg3;

<<<<<<< HEAD
import android.content.Intent;
=======
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
>>>>>>> 61ba2b5 (no message)
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
<<<<<<< HEAD
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

=======
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication.R;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uyg3_urundetay);

        tanimlamalar();

        Intent i=getIntent();
        id=i.getIntExtra( "id", 0);
        try{
            database=this.openOrCreateDatabase( "Urun",MODE_PRIVATE, null);
            Cursor cursor=database.rawQuery( "SELECT * FROM urunler WHERE id=?",new String[]{String.valueOf(id)});
            int kolonUrunAdi=cursor.getColumnIndex( "urunadi");
            int kolonFiyat=cursor.getColumnIndex( "fiyat");
            int kolonAdet=cursor.getColumnIndex( "resim");
            while (cursor.moveToNext()){
                textViewUrunadi.setText(cursor.getString(kolonUrunAdi));
                textViewUrunFiyat.setText(cursor.getString(kolonFiyat)+"");
                textViewUrunAdet.setText(cursor.getString(kolonAdet));
                byte[]bytes=cursor.getBlob(kolonResim);
                Bitmap bitmap= BitmapFactory.decodeByteArray(bytes, 0,bytes.length);
                urunResim.setImageBitmap(bitmap);
            }
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }


        Log.d( "SERVİS", "onCreate"+ ContextCompat.checkSelfPermission( this, Manifest.permission.READ_EXTERNAL_STORAGE));
        registerLauncher();
        if(ContextCompat.checkSelfPermission( this,Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale( this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(this, "İzin gerekli", Toast.LENGTH_SHORT).show();
            }else{
                galleryPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }

        btnResimEkle.setOnClickListener({
                @Override
                public void onClick(View view){
                    Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    galleryLauncher.launch(i);
        }
        });


        btnDegistir.setOnClickListener({
                @Override
                public void onClick(View view){
                    Intent i=new Intent(getAplicationContext(),UrunKayit.class);
                    i.putExtra( "mod", "degistir");
                    i.putExtra( "id",id);
                    startActivity(i);
                    finish();
        }
        });

    }

    private void tanimlamalar() {

    }
}
>>>>>>> 61ba2b5 (no message)
