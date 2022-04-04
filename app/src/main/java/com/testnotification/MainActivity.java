package com.testnotification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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