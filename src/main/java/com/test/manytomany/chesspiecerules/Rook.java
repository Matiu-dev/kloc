package com.test.manytomany.chesspiecerules;

import com.test.manytomany.model.GamePlay;
import com.test.manytomany.model.MoveStatus;
import com.test.manytomany.model.Pieces;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Rook {

    private static final int COLUMN = 0;
    private static final int ROW = 1;
    private CheckSpaceBetween checkSpaceBetween;

    public Rook(){
        checkSpaceBetween = new CheckSpaceBetween();
    }

    public GamePlay checkMoveWhite(GamePlay gamePlay) {

        char[] oldF = gamePlay.getCoordinateOld().toCharArray();
        char[] newF = gamePlay.getCoordinateNew().toCharArray();

        //sprawdzanie ruchu do przodu

        if(oldF[COLUMN] == newF[COLUMN] && oldF[ROW] < newF[ROW]) {
            if(checkSpaceBetween.checkPieceBetweenUp(oldF, newF, gamePlay)){

                if(gamePlay.getCoordinateOld().equals("11")){
                    boolean[] castling = new boolean[3];
                    castling[0] = gamePlay.getCastling()[0];
                    castling[1] = false;
                    castling[2] = gamePlay.getCastling()[2];
                    gamePlay.setCastling(castling);
                }

                if(gamePlay.getCoordinateOld().equals("81")){
                    boolean[] castling = new boolean[3];
                    castling[0] = gamePlay.getCastling()[0];
                    castling[1] = gamePlay.getCastling()[1];
                    castling[2] = false;
                    gamePlay.setCastling(castling);
                }

                gamePlay.setFigureNameNew(Pieces.WHITEROOK.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
                log.info("good move white rook up");
                gamePlay.setMoveStatus(MoveStatus.OK);


            }else {
                log.info("bad move white rook up");
                gamePlay.setMoveStatus(MoveStatus.BAD);


            }
            return gamePlay;
        }

        //sprawdzaniu ruchu w tył

        if(oldF[COLUMN] == newF[COLUMN] && oldF[ROW] > newF[ROW]) {
            if(checkSpaceBetween.checkPieceBetweenDown(oldF, newF, gamePlay)){

                if(gamePlay.getCoordinateOld().equals("11")){
                    boolean[] castling = new boolean[3];
                    castling[0] = gamePlay.getCastling()[0];
                    castling[1] = false;
                    castling[2] = gamePlay.getCastling()[2];
                    gamePlay.setCastling(castling);
                }

                if(gamePlay.getCoordinateOld().equals("81")){
                    boolean[] castling = new boolean[3];
                    castling[0] = gamePlay.getCastling()[0];
                    castling[1] = gamePlay.getCastling()[1];
                    castling[2] = false;
                    gamePlay.setCastling(castling);
                }

                gamePlay.setFigureNameNew(Pieces.WHITEROOK.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
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
            if(checkSpaceBetween.checkPieceBetweenRight(oldF, newF, gamePlay)){

                if(gamePlay.getCoordinateOld().equals("11")){
                    boolean[] castling = new boolean[3];
                    castling[0] = gamePlay.getCastling()[0];
                    castling[1] = false;
                    castling[2] = gamePlay.getCastling()[2];
                    gamePlay.setCastling(castling);
                }

                if(gamePlay.getCoordinateOld().equals("81")){
                    boolean[] castling = new boolean[3];
                    castling[0] = gamePlay.getCastling()[0];
                    castling[1] = gamePlay.getCastling()[1];
                    castling[2] = false;
                    gamePlay.setCastling(castling);
                }

                gamePlay.setFigureNameNew(Pieces.WHITEROOK.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
                log.info("good move white rook right");
                gamePlay.setMoveStatus(MoveStatus.OK);


            }else {
                log.info("bad move white rook up");
                gamePlay.setMoveStatus(MoveStatus.BAD);

            }
            return gamePlay;
        }

        //sprawdzanie ruchu w lewo

        if(oldF[ROW] == newF[ROW]  && oldF[COLUMN] > newF[COLUMN]) {
            if(checkSpaceBetween.checkPieceBetweenLeft(oldF, newF, gamePlay)){

                if(gamePlay.getCoordinateOld().equals("11")){
                    boolean[] castling = new boolean[3];
                    castling[0] = gamePlay.getCastling()[0];
                    castling[1] = false;
                    castling[2] = gamePlay.getCastling()[2];
                    gamePlay.setCastling(castling);
                }

                if(gamePlay.getCoordinateOld().equals("81")){
                    boolean[] castling = new boolean[3];
                    castling[0] = gamePlay.getCastling()[0];
                    castling[1] = gamePlay.getCastling()[1];
                    castling[2] = false;
                    gamePlay.setCastling(castling);
                }

                gamePlay.setFigureNameNew(Pieces.WHITEROOK.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
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

        if(oldF[COLUMN] == newF[COLUMN] && oldF[ROW] < newF[ROW]) {
            if(checkSpaceBetween.checkPieceBetweenUp(oldF, newF, gamePlay)){

                if(gamePlay.getCoordinateOld().equals("18")){
                    boolean[] castling = new boolean[3];
                    castling[0] = gamePlay.getCastling()[0];
                    castling[1] = false;
                    castling[2] = gamePlay.getCastling()[2];
                    gamePlay.setCastling(castling);
                }

                if(gamePlay.getCoordinateOld().equals("88")){
                    boolean[] castling = new boolean[3];
                    castling[0] = gamePlay.getCastling()[0];
                    castling[1] = gamePlay.getCastling()[1];
                    castling[2] = false;
                    gamePlay.setCastling(castling);
                }


                gamePlay.setFigureNameNew(Pieces.BLACKROOK.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
                log.info("good move black rook up");
                gamePlay.setMoveStatus(MoveStatus.OK);


            }else {
                log.info("bad move black rook up");
                gamePlay.setMoveStatus(MoveStatus.BAD);
            }
            return gamePlay;
        }

        //sprawdzaniu ruchu w tył

        if(oldF[COLUMN] == newF[COLUMN] && oldF[ROW] > newF[ROW]) {
            if(checkSpaceBetween.checkPieceBetweenDown(oldF, newF, gamePlay)){

                if(gamePlay.getCoordinateOld().equals("18")){
                    boolean[] castling = new boolean[3];
                    castling[0] = gamePlay.getCastling()[0];
                    castling[1] = false;
                    castling[2] = gamePlay.getCastling()[2];
                    gamePlay.setCastling(castling);
                }

                if(gamePlay.getCoordinateOld().equals("88")){
                    boolean[] castling = new boolean[3];
                    castling[0] = gamePlay.getCastling()[0];
                    castling[1] = gamePlay.getCastling()[1];
                    castling[2] = false;
                    gamePlay.setCastling(castling);
                }

                gamePlay.setFigureNameNew(Pieces.BLACKROOK.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
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
            if(checkSpaceBetween.checkPieceBetweenRight(oldF, newF, gamePlay)){

                if(gamePlay.getCoordinateOld().equals("18")){
                    boolean[] castling = new boolean[3];
                    castling[0] = gamePlay.getCastling()[0];
                    castling[1] = false;
                    castling[2] = gamePlay.getCastling()[2];
                    gamePlay.setCastling(castling);
                }

                if(gamePlay.getCoordinateOld().equals("88")){
                    boolean[] castling = new boolean[3];
                    castling[0] = gamePlay.getCastling()[0];
                    castling[1] = gamePlay.getCastling()[1];
                    castling[2] = false;
                    gamePlay.setCastling(castling);
                }

                gamePlay.setFigureNameNew(Pieces.BLACKROOK.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
                log.info("good move black rook right");
                gamePlay.setMoveStatus(MoveStatus.OK);

            }else {
                log.info("bad move black rook up");
                gamePlay.setMoveStatus(MoveStatus.BAD);
            }
            return gamePlay;
        }

        //sprawdzanie ruchu w lewo

        if(oldF[ROW] == newF[ROW]  && oldF[COLUMN] > newF[COLUMN]) {
            if(checkSpaceBetween.checkPieceBetweenLeft(oldF, newF, gamePlay)){

                if(gamePlay.getCoordinateOld().equals("18")){
                    boolean[] castling = new boolean[3];
                    castling[0] = gamePlay.getCastling()[0];
                    castling[1] = false;
                    castling[2] = gamePlay.getCastling()[2];
                    gamePlay.setCastling(castling);
                }

                if(gamePlay.getCoordinateOld().equals("88")){
                    boolean[] castling = new boolean[3];
                    castling[0] = gamePlay.getCastling()[0];
                    castling[1] = gamePlay.getCastling()[1];
                    castling[2] = false;
                    gamePlay.setCastling(castling);
                }

                gamePlay.setFigureNameNew(Pieces.BLACKROOK.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
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
}
