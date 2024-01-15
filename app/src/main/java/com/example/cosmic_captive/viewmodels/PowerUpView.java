package com.example.cosmic_captive.viewmodels;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.example.cosmic_captive.R;
import com.example.cosmic_captive.model.PowerUp;

public class PowerUpView extends View {
    private PowerUp powerUp;
    private Bitmap sprite;
    private Paint paint;

    public PowerUpView(Context context, PowerUp powerUp) {
        super(context);
        this.powerUp = powerUp;
        loadSprite(context, powerUp.getType());
        this.paint = new Paint();
    }

    private void loadSprite(Context context, int type) {
        int resourceId;
        switch (type) {
        case 1:
            resourceId = R.drawable.powerup_speed;
            break;
        case 2:
            resourceId = R.drawable.powerup_health;
            break;
        case 3:
            resourceId = R.drawable.powerup_shield;
            break;
        default:
            resourceId = R.drawable.powerup_shield;
        }
        this.sprite = BitmapFactory.decodeResource(context.getResources(), resourceId);
        this.sprite = Bitmap.createScaledBitmap(this.sprite, PowerUp.WIDTH, PowerUp.HEIGHT, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(sprite, powerUp.getX(), powerUp.getY(), paint);
    }

    public PowerUp getPowerUp() {
        return powerUp;
    }
}
