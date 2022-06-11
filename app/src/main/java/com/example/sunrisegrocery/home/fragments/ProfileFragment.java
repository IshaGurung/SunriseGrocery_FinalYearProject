package com.example.sunrisegrocery.home.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sunrisegrocery.R;
import com.example.sunrisegrocery.admin.AdminActivity;
import com.example.sunrisegrocery.home.fragments.more.AboutUsActivity;
import com.example.sunrisegrocery.home.fragments.more.ContactUsActivity;
import com.example.sunrisegrocery.home.fragments.more.ProfileActivity;
import com.example.sunrisegrocery.order.orderDetails.OrderHistoryActivity;
import com.example.sunrisegrocery.userAccount.UserAccountActivity;
import com.example.sunrisegrocery.utils.SharedPrefUtils;


public class ProfileFragment extends Fragment {
    TextView logOutTV;
    TextView profileTV;
    TextView adminAreaTV;
    TextView policiesTV, OrderhistoryTV;
    TextView aboutusTV, contactusTV;
    Window window;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logOutTV = view.findViewById(R.id.logOutTV);
        profileTV = view.findViewById(R.id.profileTV);
        adminAreaTV = view.findViewById(R.id.adminAreaTV);
        aboutusTV = view.findViewById(R.id.aboutusTV);
        OrderhistoryTV = view.findViewById(R.id.OrderhistoryTV);

        orderhistoryOnClick();
        contactusTV = view.findViewById(R.id.contactusTV);
        contactusOnClick();
        checkAdmin();
        setClickListeners();
        PoliciesOnClick();
        ProfileOnClick();
        orderhistoryOnClick();

    }


    private void contactusOnClick() {

        contactusTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), ContactUsActivity.class);
                startActivity(intent);
            }
        });
    }


    private void PoliciesOnClick() {
        aboutusTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkAdmin() {
        boolean is_staff = SharedPrefUtils.getBool(getActivity(), getString(R.string.staff_key), false);
        if (is_staff)
            adminAreaTV.setVisibility(View.VISIBLE);

    }

    private void ProfileOnClick() {
        profileTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);

            }
        });
    }
    private void orderhistoryOnClick() {
        OrderhistoryTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OrderHistoryActivity.class);
                startActivity(intent);
            }
        });
    }
    private void setClickListeners() {
        logOutTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPrefUtils.clear(getContext());
                Intent userAccount = new Intent(getContext(), UserAccountActivity.class);
                startActivity(userAccount);
                getActivity().finish();
            }
        });
        adminAreaTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AdminActivity.class);
                startActivity(intent);
            }
        });


    }
}