package com.example.qr_scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button generateqrbtn,scanqrbtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        generateqrbtn=findViewById(R.id.btngenarate);
        scanqrbtn = findViewById(R.id.btnscan);
        generateqrbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i =new Intent(HomeActivity.this,Generateqr.class);
                startActivity(i);
            }
        });
        scanqrbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i =new Intent(HomeActivity.this,Scanqr.class);
                startActivity(i);
            }
        });

    }
}