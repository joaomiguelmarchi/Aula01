package com.example.myapplication;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }
    public void onCalcular(View v){
        Intent intent = new Intent(getApplicationContext(),Activity2.class);
        startActivity(intent);
    }
    public void onSobre(View v){
        Intent intent = new Intent(getApplicationContext(),ActivitySobre.class);
        startActivity(intent);
    }
}