package com.ait.dboshko1.minesweeper.ui;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.ait.dboshko1.minesweeper.R;
import com.ait.dboshko1.minesweeper.model.Field;
import com.ait.dboshko1.minesweeper.model.MSModel;

public class MSView extends View {


    private Paint paintBackGround;
    private Paint paintHiddenTile;
    private Paint paintNumber;
    private Bitmap bitmapMine;


    private final int msBoardWidth;
    private final int msBoardHeight;



    public MSView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paintBackGround = new Paint();
        paintBackGround.setColor(Color.rgb(230,230,230));

        paintHiddenTile = new Paint();
        paintHiddenTile.setColor(Color.rgb(191,191,191));

        paintNumber = new Paint();
        paintNumber.setColor(Color.BLACK);
        paintNumber.setStyle(Paint.Style.FILL_AND_STROKE);
        paintNumber.setStrokeWidth(2);
        paintNumber.setTypeface(Typeface.MONOSPACE);
        paintNumber.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                32, getResources().getDisplayMetrics()));

        bitmapMine = BitmapFactory.decodeResource(getResources(), R.drawable.mine);
        msBoardWidth = MSModel.getInstance().getBoardWidth();
        msBoardHeight = MSModel.getInstance().getBoardHeight();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmapMine = Bitmap.createScaledBitmap(bitmapMine, getWidth() / msBoardWidth,
                getHeight() / msBoardHeight, false);

    }

    //TODO: Add the scaling thing that I don't understand

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBackGround);
        drawBoard(canvas);
    }

    private void drawBoard(Canvas canvas) {
        for (int i = 0; i < msBoardWidth; i++) {
            for (int j = 0; j < msBoardHeight; j++) {
                drawTile(canvas, i, j);
            }
        }
    }

    private void drawTile(Canvas canvas, int i, int j) {
        Field f = MSModel.getInstance().getFieldContent(i,j);
        if(!f.isRevealed()) {
            drawHiddenTile(canvas, i, j);
        } else {
            drawRevealedTile(canvas, i, j, f);
        }
    }

    private void drawRevealedTile(Canvas canvas, int i, int j, Field f) {
        if(f.isMine()) {
            int padding = Math.max(1, (getHeight() / msBoardHeight) / 50);
            RectF square = new RectF(j * (getWidth() / msBoardWidth) + padding, i * (getHeight() / msBoardHeight) + padding,
                    (j + 1) * (getWidth() / msBoardWidth) - padding, (i + 1) * (getHeight() / msBoardHeight) - padding);
            canvas.drawBitmap(bitmapMine, null, square, null);
        } else {
            int numMines = f.getNumAdjMines();
            if(numMines > 0) {
                canvas.drawText(Integer.toString(numMines),
                        j * (getWidth() / msBoardWidth) + (getWidth() / msBoardWidth) * 0.25f,
                        i * (getHeight() / msBoardHeight) + (getHeight() / msBoardHeight) * 0.5f
                        , paintNumber);
            }
        }
    }

    private void drawHiddenTile(Canvas canvas, int i, int j) {
        int padding = Math.max(1,(getHeight() / msBoardHeight) / 50);
        RectF square = new RectF(j * (getWidth() / msBoardWidth) + padding, i * (getHeight() / msBoardHeight) + padding,
                (j + 1) * (getWidth() / msBoardWidth) - padding, (i + 1) * (getHeight() / msBoardHeight) - padding);

        canvas.drawRoundRect(square, 15, 15, paintHiddenTile);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            int i = (int) (event.getY() / (getHeight() / msBoardHeight));
            int j = (int) (event.getX() / (getWidth() / msBoardWidth));
            Log.d("touchEvent", "onTouchEvent: firing");
            MSModel.getInstance().setFieldRevealed(i,j);
        }
        invalidate();
        return super.onTouchEvent(event);
    }
}
