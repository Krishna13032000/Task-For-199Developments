package com.example.task;

import android.content.Context;

public class SharedPreferencesService {

    static String sharedPreferenceKey = "com.example.task";

    public static void setUsername(Context context,String userName){
        context.getSharedPreferences(sharedPreferenceKey,Context.MODE_PRIVATE).edit().putString("username",userName).apply();
    }

    public static String getUserName(Context context){
        return context.getSharedPreferences(sharedPreferenceKey,Context.MODE_PRIVATE).getString("username","");
    }

    public static void setPassword(Context context,String password){
        context.getSharedPreferences(sharedPreferenceKey,Context.MODE_PRIVATE).edit().putString("password",password).apply();
    }

    public static String getPassword(Context context){
        return context.getSharedPreferences(sharedPreferenceKey,Context.MODE_PRIVATE).getString("password","");
    }
    public static void setName(Context context,String name){
        context.getSharedPreferences(sharedPreferenceKey,Context.MODE_PRIVATE).edit().putString("name",name).apply();
    }

    public static String getName(Context context){
        return context.getSharedPreferences(sharedPreferenceKey,Context.MODE_PRIVATE).getString("name","");
    }

}
