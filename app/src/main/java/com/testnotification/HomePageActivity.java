package com.testnotification;

import android.app.Application;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

        Smartech.getInstance(new WeakReference<>(this)).updateUserProfile(payload);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btn_reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Location location = new Location("");
                location.setLatitude(18.9985652);
                location.setLongitude(72.8259925);
                Smartech.getInstance(new WeakReference<>(getApplicationContext())).setUserLocation(location);

            }
        });
        btn_parking_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> payload = new HashMap<>();
                payload.put("payment", "Parking Payment");
                Smartech.getInstance(new WeakReference<>(getApplicationContext())).trackEvent("Parking Payment", payload);

            }
        });
        btn_addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> payload = new HashMap<>();
                payload.put("cart", "Add_Cart");
                Smartech.getInstance(new WeakReference<>(getApplicationContext())).trackEvent("Cart_Details", payload);

            }
        });
        btn_update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // custom evnet
                HashMap<String, Object> payload = new HashMap<>();
                payload.put("name", "Update_Profile");
                Smartech.getInstance(new WeakReference<>(getApplicationContext())).trackEvent("Update_Profile", payload);
                Toast.makeText(getApplicationContext(),"Location enabled",Toast.LENGTH_SHORT).show();
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