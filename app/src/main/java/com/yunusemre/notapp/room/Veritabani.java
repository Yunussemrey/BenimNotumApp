package com.yunusemre.notapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.yunusemre.notapp.data.entity.Notlar;

@Database(entities = {Notlar.class},version = 1)
public abstract class Veritabani extends RoomDatabase {

    public abstract NotlarDao getNotlarDao(); // burası bize interface vericek ve o da veritabanında ki verileri getirecek!!


}
