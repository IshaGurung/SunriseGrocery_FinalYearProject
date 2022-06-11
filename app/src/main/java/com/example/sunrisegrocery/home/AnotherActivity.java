package com.example.sunrisegrocery.home;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sunrisegrocery.R;

public class AnotherActivity extends AppCompatActivity {
    TextView mTitleTv, mDescTv;
    ImageView mImageIV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        ActionBar actionBar = getSupportActionBar();

        setContentView(R.layout.activity_another);
        mTitleTv = findViewById(R.id.title);
        mDescTv = findViewById(R.id.description);
        mImageIV = findViewById(R.id.imageView);

        Intent intent = getIntent();
        String mTitle = intent.getStringExtra("iTitle");
        String mDescription = intent.getStringExtra("iDesc");
        byte[] mBytes = getIntent().getByteArrayExtra("iImage");
        Bitmap bitmap = BitmapFactory.decodeByteArray(mBytes, 0, mBytes.length);

        actionBar.setTitle(mTitle);
        mTitleTv.setText(mTitle);
        mDescTv.setText(mDescription);
        mImageIV.setImageBitmap(bitmap);
    }
}