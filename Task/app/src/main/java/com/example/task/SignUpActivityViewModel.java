package com.example.task;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class SignUpActivityViewModel extends AndroidViewModel {

    public MutableLiveData<String> userName;

    public MutableLiveData<String> password;

    public MutableLiveData<String> name;

    public SignUpActivityViewModel(@NonNull Application application) {
        super(application);
        init();
    }

    public void init() {
        name = new MutableLiveData<>("");
        userName = new MutableLiveData<>("");
        password = new MutableLiveData<>("");
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public void setName(MutableLiveData<String> name) {
        this.name = name;
    }

    public MutableLiveData<String> getUserName() {
        return userName;
    }

    public void setUserName(MutableLiveData<String> userName) {
        this.userName = userName;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void setPassword(MutableLiveData<String> password) {
        this.password = password;
    }
}
