package com.example.myapplication.uyg3;

import java.sql.Blob;

public class Urun {
  private int id;
  private String urunAdi;
  private String fiyat;
  private String adet;
  private byte[] resim;

    public Urun(int id, String urunAdi, String fiyat, String adet, byte[] resim) {
        this.id = id;
        this.urunAdi = urunAdi;
        this.fiyat = fiyat;
        this.adet = adet;
        this.resim = resim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrunAdi() {
        return urunAdi;
    }


    public String getFiyat() {
        return fiyat;
    }


    public String getAdet() {
        return adet;
    }


    public byte[] getResim() {
        return resim;
    }

}

