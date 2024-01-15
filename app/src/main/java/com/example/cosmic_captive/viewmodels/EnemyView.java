package com.example.cosmic_captive.viewmodels;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;


import com.example.cosmic_captive.data.enemy.Enemies;

public class EnemyView extends View {
    private float x;
    private float y;
    private int width;
    private int height;
    private Bitmap sprite;
    private Paint paint;

    public EnemyView(Context context, Enemies enemy) {
        super(context);
        this.x = enemy.getX();
        this.y = enemy.getY();
        this.paint = new Paint();
        this.width = Enemies.WIDTH;
        this.height = Enemies.HEIGHT;

        this.sprite = BitmapFactory.decodeResource(context.getResources(), enemy.getSprite());
        this.sprite = Bitmap.createScaledBitmap(this.sprite, width, height, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(sprite, x, y, paint);
    }

    public void updatePosition(Enemies enemy) {
        x = enemy.getX();
        y = enemy.getY();
        invalidate();
    }
    public float getY() {
        return y;
    }
    public float getX() {
        return x;
    }

    public int getenemyWidth() {
        return width;
    }
    public int getenemyHeight() {
        return height;
    }
}
