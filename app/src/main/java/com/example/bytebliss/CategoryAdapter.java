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
import com.example.bytebliss.databinding.CategoryRvBinding;
import com.example.bytebliss.databinding.SearchRvBinding;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.UserViewHolder>{
    private ArrayList<Recipie> dataList;
    private Context context;

    public CategoryAdapter(Context context, ArrayList<Recipie> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    static class UserViewHolder extends RecyclerView.ViewHolder{
        CategoryRvBinding binding;
        public UserViewHolder(@NonNull CategoryRvBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryRvBinding binding = CategoryRvBinding.inflate(LayoutInflater.from(context),parent,false);
        return new CategoryAdapter.UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(dataList.get(position).getImg()).into(holder.binding.img); // for image of food

        holder.binding.tittle.setText(dataList.get(position).getTitle());   // for title

        // for time
        String paragraph = dataList.get(position).getIng();
        String[] lines = paragraph.split("\n");
        String firstLine = lines.length > 0 ? lines[0] : "";
        holder.binding.time.setText(firstLine);

        holder.binding.next.setOnClickListener(new View.OnClickListener() {
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
