package com.yunusemre.notapp.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "notlar")
public class Notlar implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "not_id")
    @NonNull
    private int not_id;
    @ColumnInfo(name = "not_baslik")
    @NonNull
    private String not_baslik;
    @ColumnInfo(name = "not_icerik")
    @NonNull
    private String not_icerik;
    @ColumnInfo(name = "tarih")
    @NonNull
    private String tarih;


    public Notlar() {
    }

    public Notlar(int not_id, String not_baslik, String not_icerik, String tarih) {
        this.not_id = not_id;
        this.not_baslik = not_baslik;
        this.not_icerik = not_icerik;
        this.tarih = tarih;
    }

    public int getNot_id() {
        return not_id;
    }

    public void setNot_id(int not_id) {
        this.not_id = not_id;
    }

    public String getNot_baslik() {
        return not_baslik;
    }

    public void setNot_baslik(String not_baslik) {
        this.not_baslik = not_baslik;
    }

    public String getNot_icerik() {
        return not_icerik;
    }

    public void setNot_icerik(String not_icerik) {
        this.not_icerik = not_icerik;
    }


    public String getTarih() {
        return tarih;
    }

    public void setTarih(@NonNull String tarih) {
        this.tarih = tarih;
    }
}
