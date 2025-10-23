package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TinderButton extends androidx.appcompat.widget.AppCompatButton {
    public TinderButton(@NonNull Context context) {
        super(context);
    }

    public TinderButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TinderButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    float colorR;
    float colorB;
    float colorG;
    int x0;
    int y0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();


        Log.d("position", "x: " + x + " y: " + y + " Event: " + event.getAction());

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            x0 = (int) x;
            y0 = (int) y;
        }

        int dx = (int) (x - x0);
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            colorR = Math.min(255, Math.max(0, 120 - dx / 5));
            colorG = Math.min(255, Math.max(0, 120 - dx / 5));
            colorB = 120;
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            colorR = 120;
            colorG = 120;
            colorB = 120;
        }

        this.setBackgroundColor(Color.rgb(colorR, colorG, colorB));

        return super.onTouchEvent(event);
    }
}
