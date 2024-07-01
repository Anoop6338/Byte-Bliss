package com.example.bytebliss;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.bytebliss.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PopularAdapter rv_Adapter;
    private ArrayList<Recipie> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupRecyclerView();

        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        binding.salad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.putExtra("TITTLE","Salad");
                intent.putExtra("CATEGORY","Salad");
                startActivity(intent);
            }
        });

        binding.mainDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.putExtra("TITTLE","Main Dish");
                intent.putExtra("CATEGORY","Dish");
                startActivity(intent);
            }
        });

        binding.desert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.putExtra("TITTLE","Desserts");
                intent.putExtra("CATEGORY","Desserts");
                startActivity(intent);
            }
        });

        binding.drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.putExtra("TITTLE","Drinks");
                intent.putExtra("CATEGORY","Drinks");
                startActivity(intent);
            }
        });

        // for bottom sheet view ----- important
        binding.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.bottom_sheet);
                dialog.show();
                dialog.getWindow().setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setGravity(Gravity.BOTTOM);


                // for github
                ImageView githubImageView = dialog.findViewById(R.id.github);
                githubImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Anoop6338"));
                        startActivity(browserIntent);
                    }
                });

                //for linkedIN
                ImageView linkedInImageView = dialog.findViewById(R.id.linkedIn);
                linkedInImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/anoop-kumar-a40880222/"));
                        startActivity(browserIntent);
                    }
                });

                //for gmail
                ImageView gmailImageView = dialog.findViewById(R.id.gmail);
                gmailImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "kumaranoop6338@gmail.com", null));
                        startActivity(Intent.createChooser(emailIntent, "From recipe app"));
                    }
                });


                // for instagram
                ImageView instaImageView = dialog.findViewById(R.id.insta);
                instaImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/anoop_6338/"));
                        startActivity(browserIntent);
                    }
                });
            }

        });

    }

    private void setupRecyclerView() {
        dataList= new ArrayList();
        binding.rvPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "db_name")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .createFromAsset("recipe.db")
                .build();

        Dao daoObject = db.dao();
        List<Recipie> recipes = daoObject.getAllUsers();

        for(Recipie i : recipes){
            if(i!=null && i.getCategory().contains("Popular")){
                dataList.add(i);
            }
            rv_Adapter =new  PopularAdapter(this,dataList);
            binding.rvPopular.setAdapter(rv_Adapter);

        }
    }
}
