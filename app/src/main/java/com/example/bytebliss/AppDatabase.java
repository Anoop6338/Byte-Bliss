package com.example.bytebliss;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Recipie.class},exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract Dao dao() ;

}
