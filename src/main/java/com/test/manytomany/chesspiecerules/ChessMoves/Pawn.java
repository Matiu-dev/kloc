package com.test.manytomany.chesspiecerules.ChessMoves;

import com.test.manytomany.chesspiecerules.CheckSpaceBetween;
import com.test.manytomany.model.GamePlay.GamePlay;
import com.test.manytomany.model.MoveStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Pawn implements ChessMovesInterface {

    private static final int COLUMN = 0;
    private static final int ROW = 1;

    private static final char STARTINGROWWHITE = '2';
    private static final char STARTINGROWBLACK = '7';

    private CheckSpaceBetween checkSpaceBetween;

    public Pawn(){
        checkSpaceBetween = new CheckSpaceBetween();
    }

    public GamePlay checkMoveWhite(GamePlay gamePlay) {

        char[] oldF = gamePlay.getCoordinateOld().toCharArray();
        char[] newF = gamePlay.getCoordinateNew().toCharArray();
        char[] enPassand;
        if(!gamePlay.getEnPassantCord().equals("")  && gamePlay.getEnPassantCord() != null){
            enPassand = gamePlay.getEnPassantCord().toCharArray();
        }else {
            enPassand = "".toCharArray();
        }

        //bicie na ukos
        if (oldF[COLUMN] + 1 == newF[COLUMN]
                && oldF[ROW] + 1 == newF[ROW]
                && !gamePlay.getFigureNameNew().isEmpty()||
                oldF[COLUMN] - 1 == newF[COLUMN]
                        && oldF[ROW] + 1 == newF[ROW]
                        && !gamePlay.getFigureNameNew().isEmpty()) {

            gamePlay.setAlgebraicNotationFirst( gamePlay.getAlgebraicNotationFirst() +
                    gamePlay.getCoordinateOld().toCharArray()[0] +
                    "x" + gamePlay.getCoordinateNew() + ";");

            gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
            log.info("good attack move white pawn bias");
            gamePlay.setMoveStatus(MoveStatus.OK);

            if(gamePlay.getCoordinateNew().toCharArray()[1]=='8') {
                gamePlay.setFigureNameNew(gamePlay.getPromoFigure());
            } else {
                gamePlay.setFigureNameNew("♙");
            }

            gamePlay.setFigureNameOld("");

            gamePlay.setEnPassantCord("");

            return gamePlay;
        }  else {
            log.info("bad move white pawn");
            gamePlay.setMoveStatus(MoveStatus.BAD);
        }

        //bicie w przelocie
        if(enPassand.length==2){

            if (oldF[COLUMN] + 1 == newF[COLUMN]
                    && Character.getNumericValue(oldF[COLUMN]) + 1 == Character.getNumericValue(enPassand[COLUMN])
                    && oldF[ROW] + 1 == newF[ROW]
                    && Character.getNumericValue(oldF[ROW]) + 1 == Character.getNumericValue(enPassand[ROW])
                    && gamePlay.getFigureNameNew().isEmpty()
                    || oldF[COLUMN] - 1 == newF[COLUMN]
                    && Character.getNumericValue(oldF[COLUMN]) - 1 == Character.getNumericValue(enPassand[COLUMN])
                    && oldF[ROW] + 1 == newF[ROW]
                    && Character.getNumericValue(oldF[ROW]) + 1 == Character.getNumericValue(enPassand[ROW])
                    && gamePlay.getFigureNameNew().isEmpty()) {

                gamePlay.setAlgebraicNotationFirst(gamePlay.getAlgebraicNotationFirst() +
                        gamePlay.getCoordinateNew().toCharArray()[0] +
                                "x" + gamePlay.getCoordinateNew() + " e.p.;");

                gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
                log.info("good move white enPassant ");
                gamePlay.setMoveStatus(MoveStatus.OK);

//                gamePlay.setCoordinateOld();
                gamePlay.setFigureNameOld("");

                gamePlay.setCoordinateNew(gamePlay.getEnPassantCord());
                gamePlay.setFigureNameNew("♙");


                gamePlay.setEnPassantCord("");
                gamePlay.setEnPassantMove(true);

                gamePlay.setAlgebraicNotationFirst(
                        gamePlay.getAlgebraicNotationFirst()
                                + gamePlay.getCoordinateNew() + ";");

                return gamePlay;
            }  else {
                log.info("bad move white pawn");
                gamePlay.setMoveStatus(MoveStatus.BAD);
            }
        }

        //      ruch do przodu
        if (oldF[ROW] + 1 == newF[ROW]
                && oldF[COLUMN] == newF[COLUMN]
                && gamePlay.getFigureNameNew().isEmpty()) {

            gamePlay.setAlgebraicNotationFirst(gamePlay.getAlgebraicNotationFirst() +
                    gamePlay.getCoordinateNew() + ";");

            gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
            log.info("good move white pawn one step forward");
            gamePlay.setMoveStatus(MoveStatus.OK);

            if(gamePlay.getCoordinateNew().toCharArray()[1]=='8') {
                gamePlay.setFigureNameNew(gamePlay.getPromoFigure());
            } else {
                gamePlay.setFigureNameNew("♙");
            }

            gamePlay.setFigureNameOld("");

            gamePlay.setEnPassantCord("");

            return gamePlay;
        } else {
            log.info("bad move white pawn");
            gamePlay.setMoveStatus(MoveStatus.BAD);

        }

        // ruch do przodu 2 pola
        if(oldF[ROW] + 2 == newF[ROW]
                && oldF[COLUMN] == newF[COLUMN]
                && oldF[ROW] == STARTINGROWWHITE
                && gamePlay.getFigureNameNew().isEmpty()) {

            gamePlay.setAlgebraicNotationFirst(gamePlay.getAlgebraicNotationFirst() +
                    gamePlay.getCoordinateNew() + ";");

            gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
            log.info("good move white pawn two steps forward");
            gamePlay.setMoveStatus(MoveStatus.OK);
            gamePlay.setFigureNameNew("♙");
            gamePlay.setFigureNameOld("");

            enPassand = gamePlay.getCoordinateOld().toCharArray();
            gamePlay.setEnPassantCord(String.valueOf(enPassand[0])+
                    String.valueOf(Character.getNumericValue(enPassand[1]) +1 ));

            return gamePlay;

        } else {
            log.info("bad move white pawn");
            gamePlay.setMoveStatus(MoveStatus.BAD);
        }

        return gamePlay;
    }

    public GamePlay checkMoveBlack(GamePlay gamePlay) {

        char[] oldF = gamePlay.getCoordinateOld().toCharArray();
        char[] newF = gamePlay.getCoordinateNew().toCharArray();

        char[] enPassand;
        if(!gamePlay.getEnPassantCord().equals("") && gamePlay.getEnPassantCord()!=null){
            enPassand = gamePlay.getEnPassantCord().toCharArray();
        }else {
            enPassand = "".toCharArray();
        }

        //bicie na ukos
        if (oldF[COLUMN] + 1 == newF[COLUMN]
                && oldF[ROW] - 1 == newF[ROW]
                && !gamePlay.getFigureNameNew().isEmpty()
                ||
                oldF[COLUMN] - 1 == newF[COLUMN]
                        && oldF[ROW] - 1 == newF[ROW]
                        && !gamePlay.getFigureNameNew().isEmpty()) {

            gamePlay.setAlgebraicNotationFirst(gamePlay.getAlgebraicNotationFirst() +
                    gamePlay.getCoordinateOld().toCharArray()[0] +
                            "x" + gamePlay.getCoordinateNew() + ";");

            gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
            log.info("good move black pawn bias");
            gamePlay.setMoveStatus(MoveStatus.OK);

            if(gamePlay.getCoordinateNew().toCharArray()[1]=='1') {
                gamePlay.setFigureNameNew(gamePlay.getPromoFigure());
            } else {
                gamePlay.setFigureNameNew("♟");
            }

            gamePlay.setFigureNameOld("");

            gamePlay.setEnPassantCord("");

            return gamePlay;
        }else {
            log.info("bad move black pawn");
            gamePlay.setMoveStatus(MoveStatus.BAD);
        }

        //bicie w przelocie
        if(enPassand.length==2){
            if (oldF[COLUMN] + 1 == newF[COLUMN]
                    && Character.getNumericValue(oldF[COLUMN]) + 1 == Character.getNumericValue(enPassand[COLUMN])
                    && oldF[ROW] - 1 == newF[ROW]
                    && Character.getNumericValue(oldF[ROW]) - 1 == Character.getNumericValue(enPassand[ROW])
                    && gamePlay.getFigureNameNew().isEmpty()
                    || oldF[COLUMN] - 1 == newF[COLUMN]
                    && Character.getNumericValue(oldF[COLUMN]) - 1 == Character.getNumericValue(enPassand[COLUMN])
                    && oldF[ROW] - 1 == newF[ROW]
                    && Character.getNumericValue(oldF[ROW]) - 1 == Character.getNumericValue(enPassand[ROW])
                    && gamePlay.getFigureNameNew().isEmpty()) {

                gamePlay.setAlgebraicNotationFirst(gamePlay.getAlgebraicNotationFirst() +
                        gamePlay.getCoordinateNew().toCharArray()[0] +
                                "x" + gamePlay.getCoordinateNew() + " e.p.;");

                gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
                log.info("good move black enPassant move");
                gamePlay.setMoveStatus(MoveStatus.OK);

//                gamePlay.setCoordinateOld();
                gamePlay.setFigureNameOld("");

                gamePlay.setCoordinateNew(gamePlay.getEnPassantCord());
                gamePlay.setFigureNameNew("♟");


                gamePlay.setEnPassantCord("");
                gamePlay.setEnPassantMove(true);

                return gamePlay;
            }  else {
                log.info("bad move white pawn");
                gamePlay.setMoveStatus(MoveStatus.BAD);
            }
        }

        //ruch do przodu
        if (oldF[ROW] - 1 == newF[ROW]
                && oldF[COLUMN] == newF[COLUMN]
                && gamePlay.getFigureNameNew().isEmpty()) {

            gamePlay.setAlgebraicNotationFirst(gamePlay.getAlgebraicNotationFirst() +
                    gamePlay.getCoordinateNew() + ";");

            gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
            log.info("good move black pawn one step forward");
            gamePlay.setMoveStatus(MoveStatus.OK);

            if(gamePlay.getCoordinateNew().toCharArray()[1]=='1') {
                gamePlay.setFigureNameNew(gamePlay.getPromoFigure());
            } else {
                gamePlay.setFigureNameNew("♟");
            }

            gamePlay.setFigureNameOld("");

            gamePlay.setEnPassantCord("");

            return gamePlay;
        }else {
            log.info("bad move black pawn");
            gamePlay.setMoveStatus(MoveStatus.BAD);
        }

        //ruch 2 pola do przodu
        if(oldF[ROW] - 2 == newF[ROW]
                && oldF[COLUMN] == newF[COLUMN]
                && oldF[ROW] == STARTINGROWBLACK
                && gamePlay.getFigureNameNew().isEmpty()) {

            gamePlay.setAlgebraicNotationFirst(gamePlay.getAlgebraicNotationFirst() +
                    gamePlay.getCoordinateNew() + ";");

            gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
            log.info("good move black pawn two steps forward");
            gamePlay.setMoveStatus(MoveStatus.OK);
            gamePlay.setFigureNameNew("♟");
            gamePlay.setFigureNameOld("");

            enPassand = gamePlay.getCoordinateOld().toCharArray();
            gamePlay.setEnPassantCord(String.valueOf(enPassand[0])+
                    String.valueOf(Character.getNumericValue(enPassand[1]) -1 ));

            return gamePlay;
        } else {
            log.info("bad move black pawn");
            gamePlay.setMoveStatus(MoveStatus.BAD);
        }

        return gamePlay;
    }
}
