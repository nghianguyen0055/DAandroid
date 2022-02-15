package com.example.appghichu.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.appghichu.Noidung;

@Database(entities = {Noidung.class}, version = 1 )
public abstract class NoidungDatabase extends RoomDatabase {

    private static final String DATABASE_NAME  = "GhiChu.db";
    private static NoidungDatabase instance;
    public static synchronized NoidungDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), NoidungDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract NoiDungDAO noiDungDAO();
}
