package com.example.myapplication;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.buttonCalc);

        btn.setOnClickListener(v -> {
            onCalcular(v);
        });
    }

    public void onCalcular(View v) {
        Intent intent = new Intent(getApplicationContext(), Activity2.class);
        startActivity(intent);
    }
}