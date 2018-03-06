package com.ait.dboshko1.minesweeper.model;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MSModel {

    private static MSModel instance = null;
    private int gameStatus;

    public static final int GAME_WIN = 0;
    public static final int GAME_LOSE = 1;
    public static final int GAME_IN_PROGESS = 2;

    private int numMines;
    private int boardWidth;
    private int boardHeight;
    private Field model[][];
    private int remainingTiles;

    public static MSModel getInstance() {
        if(instance == null) {
            instance = new MSModel();
        }
        return instance;
    }

    private MSModel() {
        numMines = 3;
        boardWidth = 5;
        boardHeight = 5;
        remainingTiles = boardHeight * boardWidth - numMines;
        model = new Field[boardWidth][boardHeight];
        initBoard();
    }

    private void initBoard() {
        initializeEmptyFields();
        initializeMines();
        initializeNonMines();
    }

    private void initializeNonMines() {
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                if(!model[i][j].isMine()) {
                    model[i][j].setNumAdjMines(getNeighboringMines(i, j));
                }
            }
        }
    }

    private void initializeEmptyFields() {
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                model[i][j] = new Field();
            }
        }
    }

    private int getNeighboringMines(int i, int j) {
        int numMines = 0;
        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {
                if(i != 0 && j != 0) {
                    int adj_k = i + k;
                    int adj_l = j + l;
                    if(adj_k >= 0 && adj_k < boardWidth && adj_l >= 0 && adj_l < boardHeight) {
                        numMines += (model[adj_k][adj_l].isMine()) ? 1 : 0;
                    }
                }
            }
        }
        return numMines;
    }

    private void initializeMines() {
        List<Integer> mines = getRandomMines();
        for (int i = 0; i < numMines; i++) {
            int row = mines.get(i) / boardWidth;
            int col = mines.get(i) % boardWidth;
            model[row][col].setMine(true);
        }
    }

    @NonNull
    private List<Integer> getRandomMines() {
        List<Integer> mines = new ArrayList<Integer>();
        for (int i = 0; i < boardHeight * boardWidth; i++) {
            mines.add(i);
        }
        Collections.shuffle(mines);
        return mines;
    }

    public Field getFieldContent(int i, int j) {
        return model[i][j];
    }

    public void setFieldRevealed(int i, int j) {
        model[i][j].setRevealed(true);
        updateGameStatus(i,j);
    }

    private void updateGameStatus(int i, int j) {
        if(model[i][j].isMine()) {
            gameStatus = GAME_LOSE;
        } else {
            remainingTiles--;
            if(remainingTiles == 0) {
                gameStatus = GAME_WIN;
            } else {
                //TODO: implement breadth first board exploration
            }
        }
    }

    public int getGameStatus() {
        return gameStatus;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }
}
