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

public class SpecificItemAdapter extends RecyclerView.Adapter<SpecificItemAdapter.SpecificItemViewHolder>{

    List<Uri> list = new ArrayList<>();
    Context context;

    public SpecificItemAdapter(List<Uri> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SpecificItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FormDataImageViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.form_data_image_view,parent,false);
        return new SpecificItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecificItemViewHolder holder, int position) {
        holder.binding.idImageViewFormData.setImageURI(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SpecificItemViewHolder extends RecyclerView.ViewHolder{
        FormDataImageViewBinding binding;
        public SpecificItemViewHolder(@NonNull FormDataImageViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
