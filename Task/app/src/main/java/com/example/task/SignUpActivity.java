package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.task.databinding.ActivitySignUpBinding;
import com.google.android.material.snackbar.Snackbar;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding activitySignUpBinding;
    SignUpActivityViewModel signUpActivityViewModel;
    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
    String mobileNumRegex = "^[6-9]\"d{9}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        activitySignUpBinding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        signUpActivityViewModel = new ViewModelProvider(this).get(SignUpActivityViewModel.class);
        activitySignUpBinding.setViewModel(signUpActivityViewModel);
        activitySignUpBinding.setClickHandler(new ClickHandler());
    }
    public class ClickHandler{
        public void signUp(View view){
            if(signUpActivityViewModel.userName.getValue().isEmpty() || signUpActivityViewModel.password.getValue().isEmpty()){
                Snackbar.make(activitySignUpBinding.getRoot(),"Please enter username and password",Snackbar.LENGTH_SHORT).show();
                return;
            }
            if(signUpActivityViewModel.userName.getValue().matches(emailRegex)){
                if(signUpActivityViewModel.password.getValue().length()>=8 && signUpActivityViewModel.password.getValue().length()<=15) {
                    if(!Character.isLowerCase(signUpActivityViewModel.password.getValue().charAt(0))){
                        Snackbar.make(activitySignUpBinding.getRoot(),"The first letter must be a lowercase letter",Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    if (isValid(signUpActivityViewModel.password.getValue())) {
                        if (!signUpActivityViewModel.name.getValue().isEmpty()) {
                            SharedPreferencesService.setPassword(SignUpActivity.this, signUpActivityViewModel.password.getValue());
                            SharedPreferencesService.setUsername(SignUpActivity.this, signUpActivityViewModel.userName.getValue());
                            SharedPreferencesService.setName(SignUpActivity.this,signUpActivityViewModel.name.getValue());
                            finish();
                        }else{
                            Snackbar.make(activitySignUpBinding.getRoot(),"Please enter a name",Snackbar.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Snackbar.make(activitySignUpBinding.getRoot(),"Please enter a valid password",Snackbar.LENGTH_SHORT).show();
                    }
                }
                else{
                    Snackbar.make(activitySignUpBinding.getRoot(),"Password length must be greater than 8 and less than 15.",Snackbar.LENGTH_SHORT).show();
                }
            }
            else if(signUpActivityViewModel.userName.getValue().length()==10){
                if(isNumber(signUpActivityViewModel.userName.getValue())){
                    if(signUpActivityViewModel.password.getValue().length()>=8 && signUpActivityViewModel.password.getValue().length()<=15) {
                        if(!Character.isLowerCase(signUpActivityViewModel.password.getValue().charAt(0))){
                            Snackbar.make(activitySignUpBinding.getRoot(),"The first letter must be a lowercase letter",Snackbar.LENGTH_SHORT).show();
                            return;
                        }
                        if (isValid(signUpActivityViewModel.password.getValue())) {
                            if (!signUpActivityViewModel.name.getValue().isEmpty()) {
                                SharedPreferencesService.setPassword(SignUpActivity.this, signUpActivityViewModel.password.getValue());
                                SharedPreferencesService.setUsername(SignUpActivity.this, signUpActivityViewModel.userName.getValue());
                                SharedPreferencesService.setName(SignUpActivity.this,signUpActivityViewModel.name.getValue());
                                finish();
                            }else{
                                Snackbar.make(activitySignUpBinding.getRoot(),"Please enter a name",Snackbar.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Snackbar.make(activitySignUpBinding.getRoot(),"Please enter a valid password",Snackbar.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Snackbar.make(activitySignUpBinding.getRoot(),"Password length must be greater than 8 and less than 15.",Snackbar.LENGTH_SHORT).show();
                    }
                }
                else{
                    Snackbar.make(activitySignUpBinding.getRoot(),"Please enter a valid username or mobile number",Snackbar.LENGTH_SHORT).show();
                }
            }
            else{
                Snackbar.make(activitySignUpBinding.getRoot(),"Please enter a valid username or mobile number",Snackbar.LENGTH_SHORT).show();
            }
        }
        public boolean isValid(String s){
            int countOfUpperCase = 0;
            int countOfDigits = 0;
            int countOfSpecialCharacters = 0;
            for(int i=0;i<s.length();i++){
                if(Character.isUpperCase(s.charAt(i)))
                    countOfUpperCase++;
                else if(Character.isDigit(s.charAt(i)))
                    countOfDigits++;
                else if(!Character.isLowerCase(s.charAt(i)))
                    countOfSpecialCharacters++;
            }
            Log.i("isvalid", "isValid: " + countOfDigits + countOfSpecialCharacters + countOfUpperCase);
            return countOfDigits>=2 && countOfUpperCase>=2 && countOfSpecialCharacters>=1;
        }
        public boolean isNumber(String s){
            int count = 0;
            for(int i=0;i<s.length();i++){
                if(Character.isDigit(s.charAt(i)))
                    count++;
            }
            return count==s.length();
        }
    }
}