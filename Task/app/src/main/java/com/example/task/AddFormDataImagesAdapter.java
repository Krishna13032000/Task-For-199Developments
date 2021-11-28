package com.example.task;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.databinding.FormDataImageViewBinding;

import java.util.ArrayList;
import java.util.List;

public class AddFormDataImagesAdapter extends RecyclerView.Adapter<AddFormDataImagesAdapter.AddFormDataViewHolder>{

    List<Uri> list = new ArrayList<>();
    Context context;

    public AddFormDataImagesAdapter(List<Uri> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AddFormDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FormDataImageViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.form_data_image_view,parent,false);
        return new AddFormDataViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AddFormDataViewHolder holder, int position) {
        holder.binding.idImageViewFormData.setImageURI(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AddFormDataViewHolder extends RecyclerView.ViewHolder{
        FormDataImageViewBinding binding;
        public AddFormDataViewHolder(@NonNull FormDataImageViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
