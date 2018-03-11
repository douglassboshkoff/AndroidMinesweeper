package com.ait.dboshko1.minesweeper.ui;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ait.dboshko1.minesweeper.MainActivity;
import com.ait.dboshko1.minesweeper.R;
import com.ait.dboshko1.minesweeper.model.Field;
import com.ait.dboshko1.minesweeper.model.MSModel;

public class MSView extends View {

    private final int msBoardWidth;
    private final int msBoardHeight;

    public MSView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        msBoardWidth = MSModel.getInstance().getBoardWidth();
        msBoardHeight = MSModel.getInstance().getBoardHeight();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        TileBitMap.initBitMaps(getContext(), getWidth() / msBoardWidth, getHeight() / msBoardHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
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
            drawHiddenTile(canvas, i, j, f);
        } else {
            drawRevealedTile(canvas, i, j, f);
        }
    }

    private void drawRevealedTile(Canvas canvas, int i, int j, Field f) {
        if(f.isMine()) {
            Bitmap tile = TileBitMap.getIconTile(TileBitMap.TileType.MINE);
            canvas.drawBitmap(tile, j * (getWidth() / msBoardWidth), i * (getHeight() / msBoardHeight), null);
        } else {
            int numMines = f.getNumAdjMines();
                Bitmap tile = TileBitMap.getNumberTile(numMines);
                canvas.drawBitmap(tile, j * (getWidth() / msBoardWidth), i * (getHeight() / msBoardHeight), null);
        }
    }

    private void drawHiddenTile(Canvas canvas, int i, int j, Field f) {
        TileBitMap.TileType type = (f.isFlagged()) ? TileBitMap.TileType.FLAG :TileBitMap.TileType.HIDDEN_TILE;
        Bitmap tile = TileBitMap.getIconTile(type);

        canvas.drawBitmap(tile, j * (getWidth() / msBoardWidth), i * (getHeight() / msBoardHeight), null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(MSModel.getInstance().getGameStatus() == MSModel.GAME_IN_PROGRESS) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                int i = (int) (event.getY() / (getHeight() / msBoardHeight));
                int j = (int) (event.getX() / (getWidth() / msBoardWidth));
                if(((MainActivity) getContext()).getRadioOption().equals(getContext().getString(R.string.reveal))) {
                    MSModel.getInstance().setFieldRevealed(i, j);
                } else {
                    MSModel.getInstance().setFieldFlagged(i, j);
                }
                checkWin();
            }
            invalidate();
        }
        return super.onTouchEvent(event);
    }

    private void checkWin() {
        ((MainActivity) getContext()).checkWin();
    }


    public void restartGame() {
        MSModel.getInstance().restartGame();
        invalidate();
    }
}
