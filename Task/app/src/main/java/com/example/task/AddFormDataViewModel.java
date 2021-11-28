package com.example.task;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class AddFormDataViewModel extends AndroidViewModel {

    public MutableLiveData<String> title;

    public MutableLiveData<String> description;

    public AddFormDataViewModel(@NonNull Application application) {
        super(application);
        init();
    }
    public void init(){
        title = new MutableLiveData<>("");
        description = new MutableLiveData<>("");
    }

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public void setTitle(MutableLiveData<String> title) {
        this.title = title;
    }

    public MutableLiveData<String> getDescription() {
        return description;
    }

    public void setDescription(MutableLiveData<String> description) {
        this.description = description;
    }
}
