package com.testnotification;

import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.netcore.android.Smartech;
import com.netcore.android.smartechbase.communication.HanselInterface;
import com.netcore.android.smartechbase.communication.SmartechInterface;
import com.netcore.android.smartechpush.SmartPush;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePageActivity extends AppCompatActivity  {


    TextView txt_username;
    Button btn_reload;
    Button btn_parking_payment;
    Button btn_addcard;
    Button btn_update_profile;
    Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        txt_username=(TextView) findViewById(R.id.txt_username);
        btn_reload=(Button)findViewById(R.id.btn_reload);
        btn_parking_payment=(Button)findViewById(R.id.btn_parking_payment);
        btn_addcard=(Button)findViewById(R.id.btn_add_card);
        btn_update_profile=(Button)findViewById(R.id.btn_update_profile);
        btn_logout=(Button)findViewById(R.id.btn_logout);

        txt_username.setText("Welcome, "+getIntent().getStringExtra("username").toString());

        HashMap<String, Object> payload = new HashMap<>();
        payload.put("FIRST NAME", getIntent().getStringExtra("username").toString());
        payload.put("LAST NAME", "");
        Smartech.getInstance(new WeakReference<>(this)).setUserIdentity("a.abdulwahab@almusbah.com");
        Smartech.getInstance(new WeakReference<>(this)).updateUserProfile(payload);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btn_reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_parking_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Smartech.getInstance(new WeakReference<>(HomePageActivity.this)).logoutAndClearUserIdentity(false);
                finish();
            }
        });
    }
    }