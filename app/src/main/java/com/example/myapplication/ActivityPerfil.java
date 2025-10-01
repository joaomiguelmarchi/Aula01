package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ActivityPerfil extends AppCompatActivity {
    public TextView txtResultado;
    public TextView txtResultado2;
    public TextView nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Bundle bundle = getIntent().getExtras();
        float valor = bundle.getFloat("msg");
        String n = bundle.getString("nome");
        txtResultado = findViewById(R.id.imcValorResultado);
        txtResultado2 = findViewById(R.id.imcResultado);
        nome = findViewById(R.id.name);
        DecimalFormat d = new DecimalFormat("#.#");
        String s = d.format(valor);
        txtResultado.setText(s);
        nome.setText(n);
        Resultado(valor);
    }

    public void Resultado(float valor){
        if(valor<=18.5){
            txtResultado2.setText("Abaixo do Peso");
        } else {
            if((valor>18.5)&&(valor<25)){
                txtResultado2.setText("Peso Ideal");
            } else{
                if ((valor>=25)&&(valor<30)){
                    txtResultado2.setText("Sobrepeso");
                } else{
                    if((valor>=30)&&(valor<35)){
                        txtResultado2.setText("Obesidade grau I");
                    } else{
                        if ((valor>=35)&&(valor<40)){
                            txtResultado2.setText("Obesidade grau II");
                        } else{
                            if((valor>=40)){
                                txtResultado2.setText("Obesidade morbida");
                            }
                        }
                    }
                }
            }
        }
    }
}