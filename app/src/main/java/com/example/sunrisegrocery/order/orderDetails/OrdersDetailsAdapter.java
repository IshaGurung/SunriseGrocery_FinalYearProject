package com.example.sunrisegrocery.order.orderDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sunrisegrocery.R;
import com.example.sunrisegrocery.api.response.Bag;
import com.example.sunrisegrocery.order.OrdersAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrdersDetailsAdapter extends RecyclerView.Adapter<OrdersDetailsAdapter.OrderViewHolder>{
    List<Bag> cartList;
    Context context;
    LayoutInflater layoutInflater;




    public OrdersDetailsAdapter(List<Bag> cartList, Context context) {
        this.cartList = cartList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public OrdersDetailsAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrdersDetailsAdapter.OrderViewHolder(layoutInflater.inflate(R.layout.item_cart_order, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersDetailsAdapter.OrderViewHolder holder, int position) {
        holder.bookNameTV.setText(cartList.get(position).getProduct().getName());
        holder.PriceTV.setText("Rs. " + cartList.get(position).getProduct().getDiscountPrice()*cartList.get(position).getQuantity() + "");
        holder.quantityTV.setText(cartList.get(position).getQuantity()+ "");
        Picasso.get().load(cartList.get(position).getProduct().getImages().get(0)).into(holder.bookImage);

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{
        TextView bookNameTV, quantityTV, PriceTV;
        LinearLayout ProductLL;
        ImageView bookImage;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            bookNameTV = itemView.findViewById(R.id.bookNameTV);
            quantityTV = itemView.findViewById(R.id.quantityTV);
            PriceTV = itemView.findViewById(R.id.PriceTV);
            bookImage = itemView.findViewById(R.id.bookImage);
            ProductLL = itemView.findViewById(R.id.ProductLL);
        }
    }

}
