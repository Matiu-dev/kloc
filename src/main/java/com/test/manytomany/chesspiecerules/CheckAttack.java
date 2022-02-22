package com.test.manytomany.chesspiecerules;

import com.test.manytomany.model.GamePlay;
import com.test.manytomany.model.MoveStatus;
import com.test.manytomany.model.Pieces;
import com.test.manytomany.model.PlayerBoard.Color;


//sprawdza czy krol jest szachowany
public class CheckAttack {

    public boolean checkAttackOnBlackKingByWhiteFigures(String attackPosition,
                                                        String[][] fobA,
                                                        String coordinateOld,
                                                        String figureNameOld,
                                                        String coordinateNew,
                                                        String figureNameNew,
                                                        Color nextMoveColor) {

        GamePlay gamePlay = new GamePlay();
        gamePlay.setCoordinateNew(attackPosition);
        gamePlay.setFiguresOnBoard(fobA);
        gamePlay.setCoordinateOld(coordinateOld);
        gamePlay.setFigureNameOld(figureNameOld);
        gamePlay.setCoordinateNew(coordinateNew);
        gamePlay.setFigureNameNew(figureNameNew);
        gamePlay.setNextMoveColor(nextMoveColor);

        String[][] fob = gamePlay.getFiguresOnBoard();

        boolean check = true;
        //sprawdzic czy ktoras z czarnych figur moze zaatakowac miejsce attackPosition z argumentu
//        String[][] fob = gamePlay.getFiguresOnBoard();

        for(int i = 1; i < 9; i++) {
            for(int j = 1; j < 9; j++) {

                if(fob[i][j].equals(Pieces.WHITEPAWN.getPiece())) {
                    gamePlay.setFigureNameOld(Pieces.WHITEPAWN.getPiece());
                    gamePlay.setCoordinateOld(String.valueOf(i)+String.valueOf(j));

                    if(new Pawn().checkMoveWhite(gamePlay).getMoveStatus().equals(MoveStatus.OK)) {
                        check = false;
                    }
                }

                if(fob[i][j].equals(Pieces.WHITEROOK.getPiece())) {
                    gamePlay.setFigureNameOld(Pieces.WHITEROOK.getPiece());
                    gamePlay.setCoordinateOld(String.valueOf(i)+String.valueOf(j));

                    if(new Rook().checkMoveWhite(gamePlay).getMoveStatus().equals(MoveStatus.OK)) {
                        check = false;
                    }
                }

                if(fob[i][j].equals(Pieces.WHITEKNIGHT.getPiece())) {
                    gamePlay.setFigureNameOld(Pieces.WHITEKNIGHT.getPiece());
                    gamePlay.setCoordinateOld(String.valueOf(i)+String.valueOf(j));

                    if(new Knight().checkMoveWhite(gamePlay).getMoveStatus().equals(MoveStatus.OK)) {
                        check = false;
                    }
                }

                if(fob[i][j].equals(Pieces.WHITEBISHOP.getPiece())) {
                    gamePlay.setFigureNameOld(Pieces.WHITEBISHOP.getPiece());
                    gamePlay.setCoordinateOld(String.valueOf(i)+String.valueOf(j));

                    if(new Bishop().checkMoveWhite(gamePlay).getMoveStatus().equals(MoveStatus.OK)) {
                        check = false;
                    }
                }

                if(fob[i][j].equals(Pieces.WHITEQUEEN.getPiece())) {
                    gamePlay.setFigureNameOld(Pieces.WHITEQUEEN.getPiece());
                    gamePlay.setCoordinateOld(String.valueOf(i)+String.valueOf(j));

                    if(new Queen().checkMoveWhite(gamePlay).getMoveStatus().equals(MoveStatus.OK)) {
                        check = false;
                    }
                }

                if(fob[i][j].equals(Pieces.WHITEKING.getPiece())) {
                    gamePlay.setFigureNameOld(Pieces.WHITEKING.getPiece());
                    gamePlay.setCoordinateOld(String.valueOf(i)+String.valueOf(j));

                    if(new King().checkMoveWhite(gamePlay).getMoveStatus().equals(MoveStatus.OK)) {
                        check = false;
                    }
                }
            }
        }

        return check;
    }

    //czarny atakuje bialego pola
    public boolean checkAttackOnWhiteKingByBlackFigures(String attackPosition,
                                                        String[][] fobA,
                                                        String coordinateOld,
                                                        String figureNameOld,
                                                        String coordinateNew,
                                                        String figureNameNew,
                                                        Color nextMoveColor) {

        GamePlay gamePlay = new GamePlay();
        gamePlay.setCoordinateNew(attackPosition);
        gamePlay.setFiguresOnBoard(fobA);
        gamePlay.setCoordinateOld(coordinateOld);
        gamePlay.setFigureNameOld(figureNameOld);
        gamePlay.setCoordinateNew(coordinateNew);
        gamePlay.setFigureNameNew(figureNameNew);
        gamePlay.setNextMoveColor(nextMoveColor);

        String[][] fob = gamePlay.getFiguresOnBoard();

        boolean check = true;
        //sprawdzic czy ktoras z czarnych figur moze zaatakowac miejsce attackPosition z argumentu
//        String[][] fob = gamePlay.getFiguresOnBoard();

        for(int i = 1; i < 9; i++) {
            for(int j = 1; j < 9; j++) {

                if(fob[i][j].equals(Pieces.BLACKPAWN.getPiece())) {
                    gamePlay.setFigureNameOld(Pieces.BLACKPAWN.getPiece());
                    gamePlay.setCoordinateOld(String.valueOf(i)+String.valueOf(j));

                    if(new Pawn().checkMoveBlack(gamePlay).getMoveStatus().equals(MoveStatus.OK)) {
                        check = false;
                    }
                }

                if(fob[i][j].equals(Pieces.BLACKROOK.getPiece())) {
                    gamePlay.setFigureNameOld(Pieces.BLACKROOK.getPiece());
                    gamePlay.setCoordinateOld(String.valueOf(i)+String.valueOf(j));

                    if(new Rook().checkMoveBlack(gamePlay).getMoveStatus().equals(MoveStatus.OK)) {
                        check = false;
                    }
                }

                if(fob[i][j].equals(Pieces.BLACKKNIGHT.getPiece())) {
                    gamePlay.setFigureNameOld(Pieces.BLACKKNIGHT.getPiece());
                    gamePlay.setCoordinateOld(String.valueOf(i)+String.valueOf(j));

                    if(new Knight().checkMoveBlack(gamePlay).getMoveStatus().equals(MoveStatus.OK)) {
                        check = false;
                    }
                }

                if(fob[i][j].equals(Pieces.BLACKBISHOP.getPiece())) {
                    gamePlay.setFigureNameOld(Pieces.BLACKBISHOP.getPiece());
                    gamePlay.setCoordinateOld(String.valueOf(i)+String.valueOf(j));

                    if(new Bishop().checkMoveBlack(gamePlay).getMoveStatus().equals(MoveStatus.OK)) {
                        check = false;
                    }
                }

                if(fob[i][j].equals(Pieces.BLACKQUEEN.getPiece())) {
                    gamePlay.setFigureNameOld(Pieces.BLACKQUEEN.getPiece());
                    gamePlay.setCoordinateOld(String.valueOf(i)+String.valueOf(j));

                    if(new Queen().checkMoveBlack(gamePlay).getMoveStatus().equals(MoveStatus.OK)) {
                        check = false;
                    }
                }

                if(fob[i][j].equals(Pieces.BLACKKING.getPiece())) {
                    gamePlay.setFigureNameOld(Pieces.BLACKKING.getPiece());
                    gamePlay.setCoordinateOld(String.valueOf(i)+String.valueOf(j));

                    if(new King().checkMoveBlack(gamePlay).getMoveStatus().equals(MoveStatus.OK)) {
                        check = false;
                    }
                }
            }
        }

        return check;
    }
}
