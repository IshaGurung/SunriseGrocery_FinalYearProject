package com.example.sunrisegrocery.order.orderDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sunrisegrocery.R;
import com.example.sunrisegrocery.api.ApiClient;
import com.example.sunrisegrocery.api.response.OrderHistory;
import com.example.sunrisegrocery.api.response.OrderHistoryResponse;
import com.example.sunrisegrocery.order.OrdersAdapter;
import com.example.sunrisegrocery.utils.SharedPrefUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity implements OrdersAdapter.OnOrderDetailsClick {
    RecyclerView orderRV;
    List<OrderHistory> data;
    OrdersAdapter orderAdapter;
    TextView backIVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        orderRV=findViewById(R.id.orderRV);
        backIVO=findViewById(R.id.backIVO);
    }
    private void historyDataCall() {
        String key = SharedPrefUtils.getString(this, getString(R.string.api_key));
        Call<OrderHistoryResponse> orderHistoryResponseCall = ApiClient.getClient().orderHistory(key);

        backIVO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        historyDataCall();
        orderHistoryResponseCall.enqueue(new Callback<OrderHistoryResponse>() {
            @Override
            public void onResponse(Call<OrderHistoryResponse> call, Response<OrderHistoryResponse> response) {
                if (response.isSuccessful()){
                    setOrderRV(response.body().getOrderHistory());
                }
            }

            @Override
            public void onFailure(Call<OrderHistoryResponse> call, Throwable t) {

            }
        });
    }

    private void setOrderRV(List<OrderHistory> orderHistoryList){
        data=orderHistoryList;
        orderRV.setHasFixedSize(true);
        orderRV.setLayoutManager(new LinearLayoutManager(this));
        orderAdapter=new OrdersAdapter(orderHistoryList,this);
        orderRV.setAdapter(orderAdapter);
    }

    public void onOrderClick(int position) {
        OrderHistory orderHistory= data.get(position);

        Intent intent=new Intent(getApplicationContext(),OrderHistoryActivity.class);
        intent.putExtra(OrderHistoryActivity.ORDER_DETAILS_KEY,  orderHistory.getBag().get(0));
        startActivity(intent);
    }
}