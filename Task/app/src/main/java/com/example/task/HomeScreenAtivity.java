package com.example.task;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.example.task.databinding.ActivityHomeScreenAtivityBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenAtivity extends AppCompatActivity {
    ActivityHomeScreenAtivityBinding binding;
    DAO dao;
    FormDatabase formDatabase;
    List<ModelForDatabase> arrayList = new ArrayList<>();
    RecyclerView recyclerView;
    HomeScreenAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home_screen_ativity);
        formDatabase = FormDatabase.getDatabase(this);
        dao = formDatabase.dao();
        binding.setClickHandler(new ClickHandler());
        recyclerView = binding.idHomeScreenRecycclerView;
        adapter = new HomeScreenAdapter(this,arrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        FormDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                arrayList = dao.getAllTheFormData();
                Log.i("tagsize", "run: " + arrayList.size());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateList(arrayList);
                    }
                });
            }
        });
    }

    public class ClickHandler{
        public void addData(View view){
            startActivity(new Intent(HomeScreenAtivity.this,AddFormData.class));
        }
    }
}