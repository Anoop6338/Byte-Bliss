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
import com.example.bytebliss.databinding.PopularRvItemBinding;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.UserViewHolder> {
    private ArrayList<Recipie> dataList;
    private Context context;

    public PopularAdapter(Context context, ArrayList<Recipie> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    static class UserViewHolder extends RecyclerView.ViewHolder{
        PopularRvItemBinding binding;
        public UserViewHolder(@NonNull PopularRvItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }



    @Override
    public PopularAdapter.UserViewHolder onCreateViewHolder( ViewGroup parent, int viewType){
         PopularRvItemBinding binding = PopularRvItemBinding.inflate(LayoutInflater.from(context),parent,false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.UserViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(dataList.get(position).getImg()).into(holder.binding.popularImg);


        try {
            holder.binding.popularTxt.setText(dataList.get(position).getTitle());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // for time
        String paragraph = dataList.get(position).getIng();
        String[] lines = paragraph.split("\n");
        String firstLine = lines.length > 0 ? lines[0] : "";
        holder.binding.popularTime.setText(firstLine);

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
