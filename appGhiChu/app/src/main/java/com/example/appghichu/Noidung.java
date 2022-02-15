package com.example.appghichu;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "noidung")
public class Noidung implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String tieude;
    private String nd;

    public Noidung(String tieude, String nd) {
        this.tieude = tieude;
        this.nd = nd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNd() {
        return nd;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }
}
