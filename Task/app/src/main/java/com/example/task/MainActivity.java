package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.task.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        activityMainBinding.setViewModel(mainActivityViewModel);
        activityMainBinding.setClickHandler(new ClickHandler());
    }

    public class ClickHandler{
        public void login(View view){
            if(!SharedPreferencesService.getUserName(MainActivity.this).isEmpty() || !SharedPreferencesService.getPassword(MainActivity.this).isEmpty()){
                if(mainActivityViewModel.userName.getValue().isEmpty() || mainActivityViewModel.password.getValue().isEmpty()){
                    Snackbar.make(activityMainBinding.getRoot(),"Username or Password is empty",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(SharedPreferencesService.getUserName(MainActivity.this).equals(mainActivityViewModel.userName.getValue()) && SharedPreferencesService.getPassword(MainActivity.this).equals(mainActivityViewModel.password.getValue())) {
                    startActivity(new Intent(MainActivity.this,HomeScreenAtivity.class));
                }
                else{
                    Snackbar.make(activityMainBinding.getRoot(),"Invalid username or password",Snackbar.LENGTH_SHORT).show();
                }
            }
            else{
                Snackbar.make(activityMainBinding.getRoot(),"Please signup to access this feature",Snackbar.LENGTH_SHORT).show();
            }
        }
        public void signUp(View view){
            startActivity(new Intent(MainActivity.this,SignUpActivity.class));
        }
    }
}