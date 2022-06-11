package com.example.sunrisegrocery.home.fragments.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sunrisegrocery.R;
import com.example.sunrisegrocery.api.response.Product;
import com.example.sunrisegrocery.singleProductpage.SingleProductActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> implements Filterable {
    List<Product> productFull;
    List<Product> searchData;
    LayoutInflater layoutInflater;
    Context context;

    public SearchAdapter(List<Product> bookListFull, Context context ){
        this.productFull = bookListFull;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        searchData = new ArrayList<>(bookListFull);
    }

    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchViewHolder(layoutInflater.inflate(R.layout.search_products, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {
        holder.SearchProductNameTV.setText(productFull.get(position).getName());
        holder.SearchOldPriceTV.setText("Rs. " +productFull.get(position).getPrice()+ "");
        holder.SearchDiscountPriceTV.setText("Rs. " + productFull.get(position).getDiscountPrice() +"");
        Picasso.get().load(productFull.get(position).getImages().get(0)).into(holder.SearchProductIV);

        holder.SearchViewLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SingleProductActivity.class);
                intent.putExtra(SingleProductActivity.DATA_KEY, productFull.get(holder.getAdapterPosition()));
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        if(productFull != null){
            return productFull.size();
        }
        return 0;
    }


    @Override
    public Filter getFilter() {
        return filter;
    }
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Product> suggestions = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0){
                suggestions.addAll(searchData);
            }else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Product item : searchData){
                    if(item.getName().toLowerCase().contains(filterPattern)
                            || item.getName().toLowerCase().contains(filterPattern)){
                        suggestions.add(item);

                    }

                }
            }
            FilterResults results = new FilterResults();
            results.values = suggestions;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            productFull.clear();
            productFull.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }

    };




    public class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView SearchProductIV;
        TextView SearchProductNameTV, SearchOldPriceTV, SearchDiscountPriceTV;
        LinearLayout SearchViewLL;


        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            SearchProductIV = itemView.findViewById(R.id.bookImageSearch);
            SearchOldPriceTV = itemView.findViewById(R.id.bookPriceSearch);
            SearchDiscountPriceTV = itemView.findViewById(R.id.SearchDiscountPriceTV);
            SearchProductNameTV = itemView.findViewById(R.id.bookNameSearch);
            SearchViewLL = itemView.findViewById(R.id.searchLayout);
        }
    }
}
