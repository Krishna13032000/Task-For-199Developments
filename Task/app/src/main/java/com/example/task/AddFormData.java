package com.example.task;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.task.databinding.ActivityAddFormDataBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class AddFormData extends AppCompatActivity {

    ActivityAddFormDataBinding binding;
    AddFormDataViewModel viewModel;
    int PICK_IMAGE_MULTIPLE = 1;
    List<Uri> imagePaths = new ArrayList<>();
    AddFormDataImagesAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_form_data);
        viewModel = new ViewModelProvider(this).get(AddFormDataViewModel.class);
        binding.setViewModel(viewModel);
        recyclerView = binding.idRecyclerView;
        findViewById(R.id.idAddImages).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                // setting type to select to be image
                intent.setType("image/*");

                // allowing multiple image to be selected
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);

            }
        });
        findViewById(R.id.idOKBUTTON).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewModel.description.getValue().length()<100){
                    Snackbar.make(binding.getRoot(),"Description must be of length 100",Snackbar.LENGTH_SHORT).show();
                }
                else if(viewModel.title.getValue().length()<5){
                    Snackbar.make(binding.getRoot(),"Title must be of length 100",Snackbar.LENGTH_SHORT).show();
                }
                else if(imagePaths.size()==0){
                    Snackbar.make(binding.getRoot(),"Plase select atleast one image",Snackbar.LENGTH_SHORT).show();
                }
                else if(viewModel.title.getValue().length()>5 && viewModel.description.getValue().length()>100 && imagePaths.size()>0){
                    Toast.makeText(AddFormData.this, "valid data", Toast.LENGTH_SHORT).show();
                    FormDatabase formDatabase = FormDatabase.getDatabase(AddFormData.this);
                    DAO dao = formDatabase.dao();
                    FormDatabase.databaseWriteExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            String s = "";
                            for(Uri uri: imagePaths){
                                s = s + uri.toString() +",";
                            }
                            ModelForDatabase modelForDatabase = new ModelForDatabase(viewModel.title.getValue(),s,viewModel.description.getValue());
                            dao.insertData(modelForDatabase);
                            finish();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && null != data) {
            // Get the Image from data
            if(imagePaths.size()!=0){
                imagePaths.clear();
            }
            if (data.getClipData() != null) {
                ClipData mClipData = data.getClipData();
                int cout = data.getClipData().getItemCount();
                for (int i = 0; i < cout; i++) {
                    // adding imageuri in array
                    Uri imageurl = data.getClipData().getItemAt(i).getUri();
                    imagePaths.add(imageurl);
                }
            } else {
                Uri imageurl = data.getData();
                imagePaths.add(imageurl);
            }
            if(imagePaths.size()>10){
                imagePaths = imagePaths.subList(0,10);
                Snackbar.make(binding.getRoot(),"You can add up to 10 images only",Snackbar.LENGTH_SHORT).show();
            }
            adapter = new AddFormDataImagesAdapter(imagePaths,this);
            recyclerView.setAdapter(adapter);
            Log.i("tagimagelist", "onActivityResult: " + imagePaths.size());
        } else {
            // show this if no image is selected
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
}