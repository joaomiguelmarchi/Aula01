package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button botao;
    EditText numeroUm, numeroDois;
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = findViewById(R.id.botao);
        numeroUm = findViewById(R.id.numeroUm);
        numeroDois = findViewById(R.id.numeroDois);
        resultado = findViewById(R.id.resultado);

        botao.setOnClickListener(view -> {
            final String txtNum1 = numeroUm.getText().toString();
            final String txtNum2 = numeroDois.getText().toString();

            if(txtNum1.isEmpty() || txtNum2.isEmpty()){
                mostraAlerta(
                        "Valor inválido",
                        "Valor mínimo e/ou valor máximo devem ser informados"
                );
                return;
            }

            final int num1 = Integer.parseInt(txtNum1);
            final int num2 = Integer.parseInt(txtNum2);

            if (num1 > num2) {
                mostraAlerta(
                        "Valor inválido",
                        "Valor mínimo não pode ser maior que o valor maximo"
                );
                return;
            }
            if(num2 == 0){
                mostraAlerta(
                        "Valor inválido",
                        "Valor maximo não pode ser zero"
                );
                return;
            }

            resultado.setText(
                    Integer.toString(gerarNumeroAleatorio(num1, num2))
            );
        });
    }

    public int gerarNumeroAleatorio(int num1, int num2) {
        final Random random = new Random();
        return random.nextInt((num2 - num1) + 1) + num1;
    }

    public void mostraAlerta(String titulo, String mensagem) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder
                .setTitle(titulo)
                .setMessage(mensagem)
                .setPositiveButton(R.string.btn_alert_ok, (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }
}