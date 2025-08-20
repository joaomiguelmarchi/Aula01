package com.example.myapplication;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ciclo_vida", "onPause");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ciclo_vida", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ciclo_vida", "onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ciclo_vida", "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("ciclo_vida", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ciclo_vida", "onDestroy");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getString(R.string.hello);
        Log.d("ciclo_vida", "onCreate");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}