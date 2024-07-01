package com.example.bytebliss;

import androidx.room.Query;

import java.util.List;
@androidx.room.Dao
public interface Dao {
    @Query("SELECT * FROM recipe")
    List<Recipie> getAllUsers();
}

