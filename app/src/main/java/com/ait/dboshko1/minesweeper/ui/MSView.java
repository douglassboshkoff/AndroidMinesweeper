package com.ait.dboshko1.minesweeper.ui;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.method.Touch;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.ait.dboshko1.minesweeper.R;
import com.ait.dboshko1.minesweeper.model.Field;
import com.ait.dboshko1.minesweeper.model.MSModel;

public class MSView extends View {


    private Paint paintBackGround;
    private Paint paintHiddenTile;
    private Bitmap bitmapMine;


    public MSView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paintBackGround = new Paint();
        paintBackGround.setColor(Color.rgb(230,230,230));

        paintHiddenTile = new Paint();
        paintHiddenTile.setColor(Color.rgb(191,191,191));

        BitmapFactory.decodeResource(getResources(), R.drawable.mine);
    }

    //TODO: Add the scaling thing that I don't understand

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBackGround);
        drawBoard(canvas);
    }

    private void drawBoard(Canvas canvas) {
        int boardWidth = MSModel.getInstance().getBoardWidth();
        int boardHeight = MSModel.getInstance().getBoardHeight();
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                drawTile(canvas, i, j, boardWidth, boardWidth);
            }
        }
    }

    private void drawTile(Canvas canvas, int i, int j, int boardWidth, int boardHeight) {
        Field f = MSModel.getInstance().getFieldContent(i,j);
        if(!f.isRevealed()) {
            drawHiddenTile(canvas, i, j, boardWidth, boardHeight);
        } else {
            drawRevealedTile(canvas, i, j, boardWidth, boardHeight, f);
        }
    }

    private void drawRevealedTile(Canvas canvas, int i, int j, int boardWidth, int boardHeight, Field f) {

    }

    private void drawHiddenTile(Canvas canvas, int i, int j, int boardWidth, int boardHeight) {
        int padding = Math.max(1,(getHeight() / boardHeight) / 50);
        RectF square = new RectF(j * (getWidth() / boardWidth) + padding, i * (getHeight() / boardHeight) + padding,
                (j + 1) * (getWidth() / boardWidth) - padding, (i + 1) * (getHeight() / boardHeight) - padding);

        canvas.drawRoundRect(square, 15, 15, paintHiddenTile);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int boardWidth = MSModel.getInstance().getBoardWidth();
        int boardHeight = MSModel.getInstance().getBoardHeight();
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            int i = (int) (event.getY() / (getHeight() / boardHeight));
            int j = (int) (event.getX() / (getWidth() / boardWidth));
            Log.d("touchEvent", "onTouchEvent: firing");
            MSModel.getInstance().setFieldRevealed(i,j);
        }
        invalidate();
        return super.onTouchEvent(event);
    }
}
