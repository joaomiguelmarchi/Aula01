package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

public class MainActivity extends AppCompatActivity {
    SimplePaint simplePaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simplePaint = findViewById(R.id.simplePaint);

        final ImageButton btnImCircle = findViewById(R.id.circle_button);
        final ImageButton btnImSquare = findViewById(R.id.square_button);
        final ImageButton btnPencil = findViewById(R.id.finger_button);
        final ImageButton btnChangeColor = findViewById(R.id.change_color_button);
        final ImageButton btnResetPaint = findViewById(R.id.clear_button);
        final ImageButton btnUndoPaint = findViewById(R.id.undo_button);

        btnImCircle.setOnClickListener(view -> simplePaint.setShape(Shapes.Circle));
        btnImSquare.setOnClickListener(view -> simplePaint.setShape(Shapes.Square));
        btnPencil.setOnClickListener(view -> simplePaint.setShape(Shapes.Finger));
        btnResetPaint.setOnClickListener(view -> simplePaint.resetPaint());
        btnUndoPaint.setOnClickListener(view -> simplePaint.undo());

        btnChangeColor.setOnClickListener(view -> simplePaint.setColorPicker());
    }
}