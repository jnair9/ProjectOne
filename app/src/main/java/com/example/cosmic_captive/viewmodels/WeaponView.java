package com.example.cosmic_captive.viewmodels;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.example.cosmic_captive.R;

public class WeaponView extends View {
    private Bitmap sprite;
    private Paint paint;
    private float x;
    private float y;

    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;

    public WeaponView(Context context, float posx, float posy) {
        super(context);
        this.paint = new Paint();
        this.x = posx;
        this.y = posy;
        sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.wrench);
        sprite = Bitmap.createScaledBitmap(this.sprite, WIDTH, HEIGHT, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(sprite, x, y, paint);
    }
    public float getregx() {
        return x;
    }
    public float getregy() {
        return y;
    }
}
