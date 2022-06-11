package com.example.sunrisegrocery.home.fragments.more;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.sunrisegrocery.R;

public class AboutUsActivity extends AppCompatActivity {
    ImageView backIV;
    ImageView aboutusbackIV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        aboutusbackIV= findViewById(R.id.aboutusbackIV);
        setOnClickListeners();
        backIV = findViewById(R.id.backIV);
        backIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
    }

    private void setOnClickListeners() {aboutusbackIV.setOnClickListener(v -> finish());
    }
}