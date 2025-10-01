package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Activity2 extends AppCompatActivity {
    public EditText txt1;
    public EditText txt2;
    public EditText name;
    public TextView aviso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        txt1 = findViewById(R.id.editPeso);
        txt2 = findViewById(R.id.editAltura);
        name = findViewById(R.id.nome);
        aviso = findViewById(R.id.alerta);
    }

    public void onCalcule(View v) {
        String peso = txt1.getText().toString();
        String altura = txt2.getText().toString();
        String n = name.getText().toString();
        try {
            float p = Integer.parseInt(peso);
            float a = Integer.parseInt(altura);
            a /= 100;
            float resultado = p / (a * a);
            Intent intent = new Intent(getApplicationContext(), ActivityResultado.class);
            intent.putExtra("msg", resultado);
            intent.putExtra("nome", n);
            startActivity(intent);
        } catch (Exception e) {
            aviso.setText("Digite numeros inteiros");
        }
    }

    public void onPerfil(View v) {
        String peso = txt1.getText().toString();
        String altura = txt2.getText().toString();
        String n = name.getText().toString();
        try {
            float p = Integer.parseInt(peso);
            float a = Integer.parseInt(altura);
            a /= 100;
            float resultado = p / (a * a);
            Intent intent = new Intent(getApplicationContext(), ActivityPerfil.class);
            intent.putExtra("msg", resultado);
            intent.putExtra("nome", n);
            startActivity(intent);
        } catch (Exception e) {
            aviso.setText("Digite numeros inteiros");
        }
    }
}