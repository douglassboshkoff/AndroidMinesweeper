package com.ait.dboshko1.minesweeper.ui;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.ait.dboshko1.minesweeper.R;

public class TileBitMap {

    private static Bitmap num_0;
    private static Bitmap num_1;
    private static Bitmap num_2;
    private static Bitmap num_3;
    private static Bitmap num_4;
    private static Bitmap num_5;
    private static Bitmap num_6;
    private static Bitmap num_7;
    private static Bitmap num_8;

    private static Bitmap flag;
    private static Bitmap mine;
    private static Bitmap hiddenTile;

    public enum TileType {
        FLAG, MINE, HIDDEN_TILE;
    }

    private TileBitMap() {
    }

    public static void initBitMaps(Context context, int width, int height) {
        num_0 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.num_0),
            width, height, false);
        num_1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.num_1),
            width, height, false);
        num_2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.num_2),
            width, height, false);
        num_3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.num_3),
            width, height, false);
        num_5 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.num_5),
            width, height, false);
        num_6 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.num_6),
            width, height, false);
        num_4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.num_4),
            width, height, false);
        num_7 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.num_7),
            width, height, false);
        num_8 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.num_8),
            width, height, false);

        mine = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.mine),
                width, height, false);
        flag = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.flag),
                width, height, false);
        hiddenTile = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.hidden_tile),
                width, height, false);
    }

    public static Bitmap getNumberTile(int number) {
        Bitmap tile = null;
        switch (number) {
            case 0: tile = num_0; break;
            case 1: tile = num_1; break;
            case 2: tile = num_2; break;
            case 3: tile = num_3; break;
            case 4: tile = num_4; break;
            case 5: tile = num_5; break;
            case 6: tile = num_6; break;
            case 7: tile = num_7; break;
            case 8: tile = num_8; break;
        }
        return tile;
    }

    public static Bitmap getIconTile(TileType type) {
        Bitmap tile = null;
        switch (type) {
            case FLAG: tile = flag; break;
            case MINE: tile = mine; break;
            case HIDDEN_TILE: tile = hiddenTile; break;
        }
        return tile;
    }


}
