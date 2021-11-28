package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;

import com.example.task.databinding.ActivitySpecificItemBinding;

import java.util.ArrayList;
import java.util.List;

public class SpecificItemActivity extends AppCompatActivity {
    ActivitySpecificItemBinding binding;
    ModelForDatabase modelForDatabase;
    int id = -1;
    RecyclerView recyclerView;
    SpecificItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_specific_item);
        id = getIntent().getIntExtra("id",-1);
        recyclerView = binding.idRecyclerViewSpecificItem;
    }

    @Override
    protected void onResume() {
        super.onResume();
        FormDatabase formDatabase = FormDatabase.getDatabase(this);
        DAO dao = formDatabase.dao();
        FormDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                modelForDatabase = dao.getSpecificData(id);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.setModel(modelForDatabase);
                        String s[] = modelForDatabase.fileUris.split(",");
                        List<Uri> list = new ArrayList<>();
                        for(String s1: s){
                            list.add(Uri.parse(s1));
                        }
                        adapter = new SpecificItemAdapter(list,SpecificItemActivity.this);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        });
    }
}