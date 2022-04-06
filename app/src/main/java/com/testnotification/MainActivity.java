package com.testnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.netcore.android.Smartech;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import io.hansel.hanselsdk.HanselActionListener;

public class MainActivity extends AppCompatActivity implements HanselActionListener {

    EditText edt_username;
    Button btn_login;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         edt_username=(EditText)findViewById(R.id.edt_username);
         btn_login=(Button) findViewById(R.id.btn_login);
         btn_register=(Button)findViewById(R.id.btn_register);
        requestPermissions();

    }

    private void requestPermissions() {
        String[] permissions = {
                Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.READ_PHONE_STATE
        };

        ActivityCompat.requestPermissions(this, permissions, 1);


    }

    @Override
    protected void onResume(){
        super.onResume();


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edt_username.getText().toString().length()>0){
                    Intent in= new Intent(MainActivity.this,HomePageActivity.class);
                    in.putExtra("username",edt_username.getText().toString());
                    startActivity(in);

                }else{
                    Toast.makeText(MainActivity.this,"Please enter username to login",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_username.getText().toString().length()>0){
                    Smartech.getInstance(new WeakReference<>(getApplicationContext())).login(edt_username.getText().toString());

                    HashMap<String, Object> payload = new HashMap<>();
                    payload.put("EMAIL", edt_username.getText().toString());
                    payload.put("MOBILE","9900000000");
                    Smartech.getInstance(new WeakReference<>(getApplicationContext())).updateUserProfile(payload);

                }else{
                    Toast.makeText(MainActivity.this,"Please enter username to register",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onActionPerformed(String s) {
        Toast.makeText(MainActivity.this,s.toString(),Toast.LENGTH_SHORT).show();
    }
}