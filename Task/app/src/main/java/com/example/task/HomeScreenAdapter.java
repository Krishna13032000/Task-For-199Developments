package com.example.task;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.databinding.RowDataBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenAdapter extends RecyclerView.Adapter<HomeScreenAdapter.HomeScreenViewHolder>{
    Context context;
    List<ModelForDatabase> list = new ArrayList<>();

    public HomeScreenAdapter(Context context, List<ModelForDatabase> list) {
        this.context = context;
        this.list = list;
    }

    public void updateList(List<ModelForDatabase> modelForDatabaseList){
        Log.i("tagsize", "updateList: ");
        list.clear();
        list.addAll(modelForDatabaseList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeScreenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowDataBinding rowDataBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.row_data,parent,false);
        return new HomeScreenViewHolder(rowDataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeScreenViewHolder holder, int position) {
        Log.i("tagsize", "onBindViewHolder: " + list.get(position).fileUris);
        holder.rowDataBinding.setModel(list.get(position));
        String s[] = list.get(position).fileUris.split(",");
        Log.i("tagsize", "onBindViewHolder: " + s[0]);
        holder.rowDataBinding.idImageView.setImageURI(Uri.parse(s[0]));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeScreenViewHolder extends RecyclerView.ViewHolder{
        RowDataBinding rowDataBinding;
        public HomeScreenViewHolder(@NonNull RowDataBinding rowDataBinding) {
            super(rowDataBinding.getRoot());
            this.rowDataBinding = rowDataBinding;
            rowDataBinding.setClickHandler(new ClickHandler());
        }
    }
    public class ClickHandler{
        public void onCLick(ModelForDatabase modelForDatabase){
            Intent intent = new Intent(context,SpecificItemActivity.class);
            intent.putExtra("id",modelForDatabase.id);
            context.startActivity(intent);
        }
    }
}
