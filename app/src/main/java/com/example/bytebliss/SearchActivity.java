package com.example.bytebliss;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.example.bytebliss.databinding.ActivityMainBinding;
import com.example.bytebliss.databinding.ActivitySearchBinding;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private @NonNull ActivitySearchBinding binding;
    private SearchAdapter rv_Adapter;
    private ArrayList<Recipie> dataList;
    private ArrayList<Recipie> recipes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.searchBtn.requestFocus();
        binding.goBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        AppDatabase db = Room.databaseBuilder(SearchActivity.this, AppDatabase.class, "db_name")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .createFromAsset("recipe.db")
                .build();

        Dao daoObject = db.dao();
        recipes = new ArrayList<>(daoObject.getAllUsers());

        setupRecyclerView();


        // text watcher -> this tell us whether the text in search bar is changed or not
        binding.searchBtn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // no need to do
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(s.toString()!=""){
                    filterData(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // no need to do
            }
        });

    }

    private void filterData(String filterText) {
        ArrayList<Recipie> filterData = new ArrayList<>();

        for (Recipie i : recipes) {
            if (i != null && i.getTitle().toLowerCase().contains(filterText.toLowerCase())) {
                filterData.add(i);
            }
            rv_Adapter.filterList(filterData);
        }
    }

    private void setupRecyclerView() {
        dataList= new ArrayList();
        binding.rvSearch.setLayoutManager(new LinearLayoutManager(this));


        for(Recipie i : recipes){
            if(i!=null && i.getCategory().contains("Popular")){
                dataList.add(i);
            }
            rv_Adapter =new SearchAdapter(this,dataList);
            binding.rvSearch.setAdapter(rv_Adapter);

        }
    }
}