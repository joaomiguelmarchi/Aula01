package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

import java.util.ArrayList;

public class SimplePaint extends View {
    ArrayList<Layer> layers;
    Shapes shape;
    CoordenadasTraco coordinates;

    public SimplePaint(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.layers = new ArrayList<>();
        this.layers.add(new Layer());
        this.shape = Shapes.Finger;

        setupLayer(getCurrentLayer());
    }

    private void setupLayer(Layer layer) {
        layer.paint.setColor(Color.BLACK);
        layer.paint.setStrokeWidth(6f);
        layer.paint.setAntiAlias(true);
        layer.paint.setStyle(Paint.Style.STROKE);
    }

    public void clear() {
        getCurrentLayer().path.reset();
        invalidate();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        for (Layer l : layers) {
            canvas.drawPath(l.path, l.paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                addLayer();
                getCurrentLayer().path.moveTo(x, y);

                coordinates = new CoordenadasTraco();
                coordinates.x = x;
                coordinates.y = y;
                return true;
            case MotionEvent.ACTION_MOVE:
                switch (this.shape) {
                    case Finger:
                        getCurrentLayer().path.lineTo(x, y);
                        break;
                    case Square:
                        getCurrentLayer().path.addRect(
                                coordinates.x,
                                coordinates.y,
                                x,
                                y,
                                Path.Direction.CW
                        );
                        break;
                    case Circle:
                        getCurrentLayer().path.reset();
                        getCurrentLayer().path.moveTo(coordinates.x, coordinates.y);

                        float radius = (float) Math.sqrt(
                                Math.pow(coordinates.x - x, 2) +
                                        Math.pow(coordinates.y - y, 2)
                        );

                        getCurrentLayer().path.addCircle(x, y, radius, Path.Direction.CW);
                        break;
                }
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    private Layer getCurrentLayer() {
        return this.layers.get(layers.size() - 1);
    }

    public void setColor(int color) {
        this.layers.add(new Layer(getCurrentLayer().paint));
        getCurrentLayer().paint.setColor(color);
    }

    private void addLayer() {
        this.layers.add(new Layer(getCurrentLayer().paint));
        invalidate();
    }

    public void setShape(Shapes shape) {
        this.shape = shape;
    }

    public void undo() {
        if (this.layers.size() > 1) {
            this.layers.remove(this.layers.size() - 1);
            invalidate();
        } else {
            clear();
        }
    }

    public void resetPaint() {
        this.layers = new ArrayList<>();
        this.layers.add(new Layer());
        this.shape = Shapes.Finger;

        setupLayer(getCurrentLayer());

        invalidate();
    }

    public void setColorPicker() {
        new ColorPickerDialog.Builder(this.getContext())
                .setTitle(R.string.chage_color_dialog)
                .setPreferenceName("MyColorPicker")
                .setPositiveButton(
                        getContext().getString(R.string.confirm),
                        (ColorEnvelopeListener) this::setLayoutColor
                ).setNegativeButton(
                        getContext().getString(R.string.confirm),
                        (dialog, i) -> dialog.dismiss()
                ).attachAlphaSlideBar(true)
                .attachBrightnessSlideBar(true)
                .setBottomSpace(12)
                .show();
    }

    public void setLayoutColor(ColorEnvelope color, boolean fromUser) {
        this.setColor(color.getColor());
    }
}

