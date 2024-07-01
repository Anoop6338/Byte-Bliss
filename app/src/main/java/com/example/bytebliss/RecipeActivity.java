package com.example.bytebliss;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.bytebliss.databinding.ActivityRecipeBinding;

public class RecipeActivity extends AppCompatActivity {
    private ActivityRecipeBinding binding;
    boolean imgCrop = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this).load(getIntent().getStringExtra("img")).into(binding.foodImg);
        binding.tittle.setText(getIntent().getStringExtra("title"));
        binding.stepsData.setText(getIntent().getStringExtra("des"));

        // for time
        String paragraph = getIntent().getStringExtra("ing").trim();
        String[] lines = paragraph.split("\n");
        String firstLine = lines.length > 0 ? lines[0] : "";
        binding.time.setText(firstLine);


        // for ingredients
        String final_ing ="";
        final_ing += lines[1] +"\n";
        for(int i=2 ;i<lines.length; i++){
            final_ing += "🟢 " + lines[i] +"\n";
        }
        binding.ingData.setText(final_ing);

        binding.fullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgCrop){
                    binding.foodImg.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    Glide.with(RecipeActivity.this).load(getIntent().getStringExtra("img")).into(binding.foodImg);
//                    binding.fullScreen.setColorFilter(Color.BLACK);
                    binding.shadow.setVisibility(View.INVISIBLE);
                    imgCrop=!imgCrop;
                }
                else{
                    binding.foodImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    Glide.with(RecipeActivity.this).load(getIntent().getStringExtra("img")).into(binding.foodImg);
//                    binding.fullScreen.setColorFilter(null);
                    binding.shadow.setVisibility(View.VISIBLE);
                    imgCrop=!imgCrop;
                }
            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.steps.setBackground(null);
        binding.steps.setTextColor(getColor(R.color.black));

        binding.steps.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                binding.steps.setTextColor(getColor(R.color.white));
                binding.steps.setBackground(getDrawable(R.drawable.btn_ing));
                binding.stepsScroll.setVisibility(View.VISIBLE);

                binding.ing.setTextColor(getColor(R.color.black));
                binding.ing.setBackground(null);
                binding.ingScroll.setVisibility(View.INVISIBLE);
            }
        });

        binding.ing.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                binding.ing.setTextColor(getColor(R.color.white));
                binding.ing.setBackground(getDrawable(R.drawable.btn_ing));
                binding.ingScroll.setVisibility(View.VISIBLE);

                binding.steps.setTextColor(getColor(R.color.black));
                binding.steps.setBackground(null);
                binding.stepsScroll.setVisibility(View.INVISIBLE);

            }
        });



    }
}