package com.example.bytebliss;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bytebliss.databinding.SearchRvBinding;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.UserViewHolder> {
    private ArrayList<Recipie> dataList;
    private Context context;

    public SearchAdapter(Context context, ArrayList<Recipie> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    static class UserViewHolder extends RecyclerView.ViewHolder{
        SearchRvBinding binding;
        public UserViewHolder(@NonNull SearchRvBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    @NonNull
    @Override
    public SearchAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SearchRvBinding binding = SearchRvBinding.inflate(LayoutInflater.from(context),parent,false);
        return new SearchAdapter.UserViewHolder(binding);
    }

    // function to update search results dynamically while searching
    @SuppressLint("NotifyDataSetChanged")
    void filterList(ArrayList<Recipie> filterList){
        dataList = filterList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getImg()).into(holder.binding.imgSearch);
        holder.binding.SearchTxt.setText(dataList.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecipeActivity.class);
                intent.putExtra("img",dataList.get(position).getImg());
                intent.putExtra("title",dataList.get(position).getTitle());
                intent.putExtra("des",dataList.get(position).getDes());
                intent.putExtra("ing",dataList.get(position).getIng());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
