package com.example.bytebliss;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import kotlin.jvm.JvmField;

@Entity(tableName ="recipe")
public class Recipie {

    @JvmField
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "uid")
    int uid =0;

    @NonNull
    @ColumnInfo(name = "img")
    private String img;

    @ColumnInfo(name = "tittle")
    @NonNull
    private String title;

    @NonNull
    @ColumnInfo(name = "des")
    private String des;

    @NonNull
    @ColumnInfo(name = "ing")
    private String ing;

    @NonNull
    @ColumnInfo(name = "category")
    private String category;



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIng() {
        return ing;
    }

    public void setIng(String ing) {
        this.ing = ing;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
