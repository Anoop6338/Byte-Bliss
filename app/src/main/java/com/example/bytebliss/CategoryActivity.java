package com.example.bytebliss;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.example.bytebliss.databinding.ActivityCategoryBinding;
import com.example.bytebliss.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryActivity extends AppCompatActivity {
    private ActivityCategoryBinding binding;
    private CategoryAdapter rv_Adapter;
    private ArrayList<Recipie> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.dishTitle.setText(getIntent().getStringExtra("TITTLE"));


        setupRecyclerView();

        binding.goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setupRecyclerView() {
        dataList= new ArrayList();
        binding.rvCategory.setLayoutManager(new LinearLayoutManager(this));
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "db_name")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .createFromAsset("recipe.db")
                .build();

        Dao daoObject = db.dao();
        List<Recipie> recipes = daoObject.getAllUsers();

        for(Recipie i : recipes){
            if(i!=null && i.getCategory().contains(Objects.requireNonNull(getIntent().getStringExtra("CATEGORY")))){
                dataList.add(i);
            }
            rv_Adapter =new CategoryAdapter(this,dataList);
            binding.rvCategory.setAdapter(rv_Adapter);

        }
    }
}