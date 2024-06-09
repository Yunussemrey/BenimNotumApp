package com.yunusemre.notapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.yunusemre.notapp.data.entity.Notlar;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface NotlarDao { // veritabanı üzerinde çalışacağımız fonk. yazacağız..
    @Query("SELECT * FROM notlar") // veritabanında ki tüm verileri getirecek!!
    Single<List<Notlar>> notlarıYukle(); // asenkron şekilde bana veri getirecek.. || okuma yapacaksak Single kullanırız


    @Insert
    Completable kaydet(Notlar not); // işlem yapacaksak Completable kullanırız.

    @Update
    Completable guncelle(Notlar not);

    @Delete
    Completable sil(Notlar not);

}
