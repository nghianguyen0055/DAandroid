package com.example.appghichu.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appghichu.Noidung;

import java.util.List;

@Dao
public interface NoiDungDAO {
    @Insert
    void InsertND(Noidung nd);

    @Query("select * from noidung")
    List<Noidung> getListND();

    @Update
    void UpdateND(Noidung nd);

    @Delete
    void DeleteNd(Noidung nd);

    @Query("select * from noidung where tieude like '%' || :TD || '%' ")
    List<Noidung> searchND(String TD);
}
