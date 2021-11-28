package com.example.task;

import android.net.Uri;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public class DataBindingAdapterUtil {

    @BindingAdapter("title")
    public static void setTitle(TextView textView, String s){
        if(s.length()>100){
            s = s.substring(0,100) +"...";
        }
        textView.setText(s);
    }

    @BindingAdapter("description")
    public static void setDescription(TextView textView, String s){
        if(s.length()>100){
            s = s.substring(0,100) +"...";
        }
        textView.setText(s);
    }


}
