package com.example.sunrisegrocery.order.orderDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sunrisegrocery.R;
import com.example.sunrisegrocery.api.ApiClient;
import com.example.sunrisegrocery.api.response.Bag;
import com.example.sunrisegrocery.api.response.OrderHistory;
import com.example.sunrisegrocery.api.response.OrderHistoryResponse;
import com.example.sunrisegrocery.api.response.Product;
import com.example.sunrisegrocery.order.OrdersAdapter;
import com.example.sunrisegrocery.utils.SharedPrefUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailsActivity extends AppCompatActivity {
    public static String key = "oKey";
    OrderHistory historyOrder;
    TextView orderId, status, orderDate, totalTV, paymentStatus, paymentStatus1, payStatus, phoneNum, delivery_area, street;
    RecyclerView cartIV;
    double deliveryCharge = 0;
    ImageView backIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        orderId = findViewById(R.id.orderId);
        cartIV = findViewById(R.id.cartIV);
        paymentStatus = findViewById(R.id.paymentStatus);
        paymentStatus1 = findViewById(R.id.paymentStatus1);
        payStatus = findViewById(R.id.payStatus);
        totalTV = findViewById(R.id.totalTV);
        orderDate = findViewById(R.id.orderDate);
        backIV = findViewById(R.id.backIv);
        phoneNum = findViewById(R.id.phoneNo);
        delivery_area = findViewById(R.id.delivery_area);
        street = findViewById(R.id.street);
        backIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if (getIntent().getSerializableExtra(key) != null) {
            historyOrder = (OrderHistory) getIntent().getSerializableExtra(key);
            setOrderHistory(historyOrder);
        }
        showOrders();
    }
    private void setOrderHistory(OrderHistory orderHistory) {
        orderId.setText("#" + orderHistory.getId() + "");
//        phoneNum.setText(orderHistory.getAddress().getPhone());
//        delivery_area.setText(orderHistory.getAddress().getCity());
//        street.setText(orderHistory.getAddress().getStreet());
        orderDate.setText(orderHistory.getOrderDateTime());
        if (orderHistory.getPaymentType() == 1){
            payStatus.setText("Cash On Delivery");
            paymentStatus.setVisibility(View.VISIBLE);
            paymentStatus1.setVisibility(View.GONE);
        }else {
            payStatus.setText("Khalti");
            paymentStatus.setVisibility(View.GONE);
            paymentStatus1.setVisibility(View.VISIBLE);
        }

    }

    private void showOrders() {
        List<Bag> cartList = historyOrder.getBag();
        cartIV.setHasFixedSize(true);
        cartIV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        OrdersDetailsAdapter ordersDetailsAdapter = new OrdersDetailsAdapter(cartList, this);
        cartIV.setAdapter(ordersDetailsAdapter);
        setPrice(cartList);
    }

    private void setPrice(List<Bag> data) {
        List<Bag> newData = data;
        double totalPrice = 0;
        for (int i = 0; i < newData.size(); i++) {
            Bag product = newData.get(i);
            int price = product.getUnitPrice();
            int q = product.getQuantity();
            totalPrice = totalPrice + price * q;
        }
        totalTV.setText("Rs. " + (totalPrice + deliveryCharge));
    }
}