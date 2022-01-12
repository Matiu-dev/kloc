package com.test.manytomany.chesspiecerules;

import com.test.manytomany.model.GamePlay;
import com.test.manytomany.model.MoveStatus;
import com.test.manytomany.model.Pieces;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Rook {

    private static final int COLUMN = 0;
    private static final int ROW = 1;

    public GamePlay checkMoveWhite(GamePlay gamePlay) {

        char[] oldF = gamePlay.getCoordinateOld().toCharArray();
        char[] newF = gamePlay.getCoordinateNew().toCharArray();

        //sprawdzanie ruchu do przodu

        if(oldF[0] == newF[0] && oldF[1] < newF[1]) {
            if(checkPieceBetweenUp(oldF, newF, gamePlay)){
                gamePlay.setFigureNameNew(Pieces.WHITEROOK.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                log.info("good move white rook up");
                gamePlay.setMoveStatus(MoveStatus.OK);


            }else {
                log.info("bad move white rook up");
                gamePlay.setMoveStatus(MoveStatus.BAD);


            }
            return gamePlay;
        }

        //sprawdzaniu ruchu w tył

        if(oldF[0] == newF[0] && oldF[1] > newF[1]) {
            if(checkPieceBetweenDown(oldF, newF, gamePlay)){
                gamePlay.setFigureNameNew(Pieces.WHITEROOK.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                log.info("good move white rook down");
                gamePlay.setMoveStatus(MoveStatus.OK);


            }else {
                log.info("bad move white rook up");
                gamePlay.setMoveStatus(MoveStatus.BAD);


            }
            return gamePlay;
        }

        //sprawdzanie ruchu w prawo

        if(oldF[ROW] == newF[ROW] && oldF[COLUMN] < newF[COLUMN]) {
            if(checkPieceBetweenRight(oldF, newF, gamePlay)){
                gamePlay.setFigureNameNew(Pieces.WHITEROOK.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                log.info("good move white rook right");
                gamePlay.setMoveStatus(MoveStatus.OK);


            }else {
                log.info("bad move white rook up");
                gamePlay.setMoveStatus(MoveStatus.BAD);

            }
            return gamePlay;
        }

        //sprawdzanie ruchu w lewo

        if(oldF[1] == newF[1]  && oldF[0] > newF[0]) {
            if(checkPieceBetweenLeft(oldF, newF, gamePlay)){
                gamePlay.setFigureNameNew(Pieces.WHITEROOK.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                log.info("good move white rook left");
                gamePlay.setMoveStatus(MoveStatus.OK);


            }else {
                log.info("bad move white rook up");
                gamePlay.setMoveStatus(MoveStatus.BAD);

            }
            return gamePlay;
        }

        return gamePlay;
    }

    public GamePlay checkMoveBlack(GamePlay gamePlay) {

        char[] oldF = gamePlay.getCoordinateOld().toCharArray();
        char[] newF = gamePlay.getCoordinateNew().toCharArray();

        //sprawdzanie ruchu do przodu

        if(oldF[0] == newF[0] && oldF[1] < newF[1]) {
            if(checkPieceBetweenUp(oldF, newF, gamePlay)){
                gamePlay.setFigureNameNew(Pieces.BLACKROOK.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                log.info("good move black rook up");
                gamePlay.setMoveStatus(MoveStatus.OK);


            }else {
                log.info("bad move black rook up");
                gamePlay.setMoveStatus(MoveStatus.BAD);
            }
            return gamePlay;
        }

        //sprawdzaniu ruchu w tył

        if(oldF[0] == newF[0] && oldF[1] > newF[1]) {
            if(checkPieceBetweenDown(oldF, newF, gamePlay)){
                gamePlay.setFigureNameNew(Pieces.BLACKROOK.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                log.info("good move black rook down");
                gamePlay.setMoveStatus(MoveStatus.OK);

            }else {
                log.info("bad move black rook up");
                gamePlay.setMoveStatus(MoveStatus.BAD);
            }
            return gamePlay;
        }

        //sprawdzanie ruchu w prawo

        if(oldF[ROW] == newF[ROW] && oldF[COLUMN] < newF[COLUMN]) {
            if(checkPieceBetweenRight(oldF, newF, gamePlay)){
                gamePlay.setFigureNameNew(Pieces.BLACKROOK.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                log.info("good move black rook right");
                gamePlay.setMoveStatus(MoveStatus.OK);

            }else {
                log.info("bad move black rook up");
                gamePlay.setMoveStatus(MoveStatus.BAD);
            }
            return gamePlay;
        }

        //sprawdzanie ruchu w lewo

        if(oldF[1] == newF[1]  && oldF[0] > newF[0]) {
            if(checkPieceBetweenLeft(oldF, newF, gamePlay)){
                gamePlay.setFigureNameNew(Pieces.BLACKROOK.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                log.info("good move black rook left");
                gamePlay.setMoveStatus(MoveStatus.OK);
            }else {
                log.info("bad move black rook up");
                gamePlay.setMoveStatus(MoveStatus.BAD);
            }
            return gamePlay;
        }

        return gamePlay;
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
}
