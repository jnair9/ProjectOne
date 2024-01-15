package com.example.cosmic_captive.viewmodels;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.cosmic_captive.R;
import com.example.cosmic_captive.data.Direction;
import com.example.cosmic_captive.model.Player;

public class PlayerView extends View {

    private float x;
    private float y;
    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;
    private Bitmap sprite;
    private Paint paint;
    private Direction dir;
    private int spritenum;
    private Context context;
    public PlayerView(Context context, float x, float y, int sprite, Direction dir) {
        super(context);
        this.x = x;
        this.y = y;
        this.paint = new Paint();
        this.dir = dir;
        this.spritenum = sprite;
        this.context = context;
        changeSprite();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(sprite, x, y, paint);
    }

    public void updatePosition(Player player, Direction dir) {
        x =  player.getPosX();
        y =  player.getPosY();
        this.dir = dir;
        changeSprite();
        invalidate();
    }
    private void changeSprite() {
        int id = R.drawable.guy_back;
        if (spritenum == 1) {
            switch (dir) {
            case UP:
                id = R.drawable.guy_back;
                break;
            case DOWN:
                id = R.drawable.spaceguyremovebg;
                break;
            case LEFT:
                id = R.drawable.guy_side;
                break;
            case RIGHT:
                id = R.drawable.guy_side_right;
                break;
            default:
                break;
            }
        } else if (spritenum == 2) {
            switch (dir) {
            case UP:
                id = R.drawable.girl_back;
                break;
            case DOWN:
                id = R.drawable.spaceguyremovebg;
                break;
            case LEFT:
                id = R.drawable.girl_side;
                break;
            case RIGHT:
                id = R.drawable.girl_side_right;
                break;
            default:
                break;
            }
        } else {
            switch (dir) {
            case UP:
                id = R.drawable.cow_back;
                break;
            case DOWN:
                id = R.drawable.cow_front;
                break;
            case LEFT:
                id = R.drawable.chunkycow;
                break;
            case RIGHT:
                id = R.drawable.chunkycow_right;
                break;
            default:
                break;
            }
        }

        sprite = BitmapFactory.decodeResource(context.getResources(), id);
        sprite = Bitmap.createScaledBitmap(this.sprite, WIDTH, HEIGHT, true);
    }
    public float getY() {
        return y;
    }
    public float getX() {
        return x;
    }



}