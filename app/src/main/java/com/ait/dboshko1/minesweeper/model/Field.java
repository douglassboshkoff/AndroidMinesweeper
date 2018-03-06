package com.ait.dboshko1.minesweeper.model;


public class Field {


    private int numAdjMines;
    private boolean isMine;
    private boolean isRevealed;
    private boolean isFlagged;

    public Field() {
        numAdjMines = 0;
        isMine = false;
        isRevealed = false;
        isFlagged = false;
    }


    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }

    public int getNumAdjMines() {
        return numAdjMines;
    }

    public void setNumAdjMines(int numAdjMines) {
        this.numAdjMines = numAdjMines;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }
}
