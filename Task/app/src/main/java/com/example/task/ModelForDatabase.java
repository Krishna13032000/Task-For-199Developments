package com.example.task;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "formData")
public class ModelForDatabase {

    @PrimaryKey(autoGenerate = true)
    int id;

    public String title;

    public String fileUris;

    public String description;

    public ModelForDatabase(String title, String fileUris, String description) {
        this.title = title;
        this.fileUris = fileUris;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileUris() {
        return fileUris;
    }

    public void setFileUris(String fileUris) {
        this.fileUris = fileUris;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
