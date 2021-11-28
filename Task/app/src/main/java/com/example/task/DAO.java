package com.example.task;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface DAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(ModelForDatabase modelForDatabase);

    @Query("SELECT * FROM formData")
    List<ModelForDatabase> getAllTheFormData();

    @Query("SELECT * FROM formData WHERE id=:id")
    ModelForDatabase getSpecificData(int id);

}
