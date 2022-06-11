package com.example.sunrisegrocery.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sunrisegrocery.R;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView mImaeView;
    TextView mTitle, mDes;
    ItemClickListener itemClickListener;

    MyHolder(@NonNull View itemView) {
        super(itemView);
        this.mImaeView = itemView.findViewById(R.id.imageTV);
        this.mTitle = itemView.findViewById(R.id.titleTV);
        this.mDes = itemView.findViewById(R.id.descriptionTv);

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClickListener(v, getLayoutPosition());
    }
    public void setItemClickListener(ItemClickListener ic){
        this.itemClickListener = ic;
    }
}
