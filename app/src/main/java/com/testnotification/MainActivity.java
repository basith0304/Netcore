package com.testnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.netcore.android.Smartech;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import io.hansel.hanselsdk.Hansel;
import io.hansel.hanselsdk.HanselActionListener;

public class MainActivity extends AppCompatActivity implements HanselActionListener {

    EditText edt_username;
    EditText edt_email;
    Button btn_login;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         edt_username=(EditText)findViewById(R.id.edt_username);
        edt_email=(EditText)findViewById(R.id.edt_email);
         btn_login=(Button) findViewById(R.id.btn_login);
         btn_register=(Button)findViewById(R.id.btn_register);
        requestPermissions();
        Smartech.getInstance(new WeakReference<>(MainActivity.this)).trackEvent("Netcore_Login", null);

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

                if(edt_username.getText().toString().length()>0 && edt_email.getText().toString().length()>0){

                    Smartech.getInstance(new WeakReference<>(MainActivity.this)).login(edt_email.getText().toString());
                    Smartech.getInstance(new WeakReference<>(MainActivity.this)).setUserIdentity(edt_email.getText().toString());

                    HashMap<String, Object> payload = new HashMap<>();
                    payload.put("EMAIL", edt_email.getText().toString());
                    payload.put("FIRSTNAME", edt_username.getText().toString());
                    payload.put("LASTNAME", "");
                    Smartech.getInstance(new WeakReference<>(MainActivity.this)).updateUserProfile(payload);
                    Hansel.getUser().setUserId(edt_email.getText().toString());

                    Intent in= new Intent(MainActivity.this,HomePageActivity.class);
                    in.putExtra("username",edt_username.getText().toString());
                    startActivity(in);
                } else{
                    Toast.makeText(MainActivity.this,"Please enter username & Email to login",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_username.getText().toString().length()>0){
                    Smartech.getInstance(new WeakReference<>(getApplicationContext())).login(edt_email.getText().toString());

                    HashMap<String, Object> payload = new HashMap<>();
                    payload.put("EMAIL", edt_email.getText().toString());
                    payload.put("FIRST NAME", edt_username.getText().toString());
                    payload.put("LAST NAME", "");
                    Smartech.getInstance(new WeakReference<>(getApplicationContext())).updateUserProfile(payload);

                }else{
                    Toast.makeText(MainActivity.this,"Please enter username to register",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.w("DEEP LINK","MainActivity Executed");


    }

    @Override
    public void onActionPerformed(String s) {
        Toast.makeText(MainActivity.this,s.toString(),Toast.LENGTH_SHORT).show();
    }
}