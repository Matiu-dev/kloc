package com.test.manytomany.chesspiecerules;

import com.test.manytomany.model.GamePlay;
import com.test.manytomany.model.PlayerBoard.Color;
import com.test.manytomany.model.game.Game;

public class CheckSpaceBetween {

    private static final int COLUMN = 0;
    private static final int ROW = 1;

    public boolean checkPieceBetweenRightUpCorner(char[] oldF, char[] newF, GamePlay gamePlay) {

        boolean check = true;
        String[][] figuresOnBoard = gamePlay.getFiguresOnBoard();

        for(int j = Character.getNumericValue(oldF[ROW])+1, i = Character.getNumericValue(oldF[COLUMN])+1;
            j <= Character.getNumericValue(newF[ROW]) && i <= Character.getNumericValue(newF[COLUMN]);
            j++,i++) {
            System.out.println("[" + i + " " + j + "]" +figuresOnBoard[i][j]);
            if(!figuresOnBoard[i][j].isEmpty() && j < Character.getNumericValue(newF[ROW])) {
                check = false;
            }
        }

        return check;
    }

    public boolean checkPieceBetweenLeftDownCorner(char[] oldF, char[] newF, GamePlay gamePlay) {

        boolean check = true;
        String[][] figuresOnBoard = gamePlay.getFiguresOnBoard();

        for(int j = Character.getNumericValue(oldF[ROW])-1, i = Character.getNumericValue(oldF[COLUMN])-1;
            j >= Character.getNumericValue(newF[ROW]) &&  i >= Character.getNumericValue(newF[COLUMN]);
            j--, i--) {
            System.out.println("[" + i + " " + j + "]" +figuresOnBoard[i][j]);
            if(!figuresOnBoard[i][j].isEmpty() && j > Character.getNumericValue(newF[ROW])){
                check = false;
            }
        }

        return check;
    }

    public boolean checkPieceBetweenLeftUpCorner(char[] oldF, char[] newF, GamePlay gamePlay) {

        boolean check = true;
        String[][] figuresOnBoard = gamePlay.getFiguresOnBoard();

        for(int j = Character.getNumericValue(oldF[COLUMN])-1, i = Character.getNumericValue(oldF[ROW]+1);
            j >= Character.getNumericValue(newF[COLUMN]) && i <= Character.getNumericValue(newF[ROW]);
            j--, i++) {
            System.out.println("[" + i + " " + j + "]" +figuresOnBoard[i][j]);
            if(!figuresOnBoard[j][i].isEmpty() && j > Character.getNumericValue(newF[COLUMN])){
                check = false;
            }
        }

        return check;
    }

    public boolean checkPieceBetweenRightBottomCorner(char[] oldF, char[] newF, GamePlay gamePlay) {

        boolean check = true;
        String[][] figuresOnBoard = gamePlay.getFiguresOnBoard();

        for(int j = Character.getNumericValue(oldF[COLUMN])+1, i = Character.getNumericValue(oldF[ROW])-1;
            j <= Character.getNumericValue(newF[COLUMN])&& i >= Character.getNumericValue(newF[ROW]);
            j++, i--) {
            System.out.println("[" + i + " " + j + "]" +figuresOnBoard[i][j]);
            if(!figuresOnBoard[j][i].isEmpty() && j < Character.getNumericValue(newF[COLUMN])){
                check = false;
            }
        }

        return check;
    }

    public boolean checkPieceBetweenUp(char[] oldF, char[] newF, GamePlay gamePlay) {

        boolean check = true;
        int i = Character.getNumericValue(oldF[COLUMN]);
        String[][] figuresOnBoard = gamePlay.getFiguresOnBoard();

        for(int j = Character.getNumericValue(oldF[ROW])+1; j <= Character.getNumericValue(newF[ROW]); j++) {
            System.out.println(figuresOnBoard[i][j]);
            if(!figuresOnBoard[i][j].isEmpty() && j < Character.getNumericValue(newF[ROW])) {
                check = false;
            }
        }

        return check;
    }

    public boolean checkPieceBetweenDown(char[] oldF, char[] newF, GamePlay gamePlay) {

        boolean check = true;
        int i = Character.getNumericValue(oldF[COLUMN]);
        String[][] figuresOnBoard = gamePlay.getFiguresOnBoard();

        for(int j = Character.getNumericValue(oldF[ROW])-1; j >= Character.getNumericValue(newF[ROW]); j--) {
            System.out.println(figuresOnBoard[i][j]);
            if(!figuresOnBoard[i][j].isEmpty() && j > Character.getNumericValue(newF[ROW])){
                check = false;
            }
        }

        return check;
    }

    public boolean checkPieceBetweenRight(char[] oldF, char[] newF, GamePlay gamePlay) {

        boolean check = true;
        int i = Character.getNumericValue(oldF[ROW]);
        String[][] figuresOnBoard = gamePlay.getFiguresOnBoard();

        for(int j = Character.getNumericValue(oldF[COLUMN])+1; j <= Character.getNumericValue(newF[COLUMN]); j++) {
            System.out.println(figuresOnBoard[j][i]);
            if(!figuresOnBoard[j][i].isEmpty() && j < Character.getNumericValue(newF[COLUMN])){
                check = false;
            }
        }

        return check;
    }

    public boolean checkPieceBetweenLeft(char[] oldF, char[] newF, GamePlay gamePlay) {

        boolean check = true;
        int i = Character.getNumericValue(oldF[ROW]);
        String[][] figuresOnBoard = gamePlay.getFiguresOnBoard();

        for(int j = Character.getNumericValue(oldF[COLUMN])-1; j >= Character.getNumericValue(newF[COLUMN]); j--) {
            System.out.println(figuresOnBoard[j][i]);
            if(!figuresOnBoard[j][i].isEmpty() && j > Character.getNumericValue(newF[COLUMN])){
                check = false;
            }
        }

        return check;
    }

    public Color changeTextMoveColor(GamePlay gamePlay) {
        if(gamePlay.getNextMoveColor().equals(Color.BLACK)){
            return Color.WHITE;
        }else if(gamePlay.getNextMoveColor().equals(Color.WHITE)){
            return Color.BLACK;
        }

        return gamePlay.getNextMoveColor();
    }
}
