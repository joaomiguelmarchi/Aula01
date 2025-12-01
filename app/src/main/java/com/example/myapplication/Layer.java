package com.example.myapplication;

import android.graphics.Paint;
import android.graphics.Path;

public class Layer {
    public Paint paint;
    public Path path;

    public Layer() {
        this.paint = new Paint();
        this.path = new Path();
    }

    public Layer(Paint paint) {
        this.paint = paint;
        this.path = new Path();
    }

    public void clear() {
        this.path.reset();
    }
}
