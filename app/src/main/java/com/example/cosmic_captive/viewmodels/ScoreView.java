package com.example.cosmic_captive.viewmodels;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class ScoreView extends View {
    private CurrentScore score;
    private Paint paint;
    public ScoreView(Context context) {
        super(context);
        score = CurrentScore.getScore();
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(40);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(score.toString(), 10, 120, paint);
    }
}

