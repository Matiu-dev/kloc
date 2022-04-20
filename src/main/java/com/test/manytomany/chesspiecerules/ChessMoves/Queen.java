package com.test.manytomany.chesspiecerules.ChessMoves;

import com.test.manytomany.chesspiecerules.CheckSpaceBetween;
import com.test.manytomany.model.GamePlay;
import com.test.manytomany.model.MoveStatus;
import com.test.manytomany.model.Pieces;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Queen implements ChessMovesInterface {


    private static final int COLUMN = 0;
    private static final int ROW = 1;
    private CheckSpaceBetween checkSpaceBetween;

    public Queen(){
        checkSpaceBetween = new CheckSpaceBetween();
    }

    public GamePlay checkMoveWhite(GamePlay gamePlay) {
        char[] oldF = gamePlay.getCoordinateOld().toCharArray();
        char[] newF = gamePlay.getCoordinateNew().toCharArray();

        //sprawdzanie ruchu do prawego gornego rogu i lewego dolnego rogu

        if(Character.getNumericValue(oldF[COLUMN]) - Character.getNumericValue(oldF[ROW]) ==
                Character.getNumericValue(newF[COLUMN]) - Character.getNumericValue(newF[ROW]) ) {
            //prawy gorny
            if(oldF[COLUMN] < newF[COLUMN] && oldF[ROW] < newF[ROW]){
                if(checkSpaceBetween.checkPieceBetweenRightUpCorner(oldF, newF, gamePlay)){
                    gamePlay.setFigureNameNew(Pieces.WHITEQUEEN.getPiece());
                    gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                    gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
                    log.info("good move white queen right corner");
                    gamePlay.setMoveStatus(MoveStatus.OK);
                }else {
                    log.info("bad move white queen right corner");
                    gamePlay.setMoveStatus(MoveStatus.BAD);
                }
                return gamePlay;
            }
            //lewy dolny

            if(oldF[COLUMN] > newF[COLUMN] && oldF[ROW] > newF[ROW]) {
                if(checkSpaceBetween.checkPieceBetweenLeftDownCorner(oldF, newF, gamePlay)){
                    gamePlay.setFigureNameNew(Pieces.WHITEQUEEN.getPiece());
                    gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                    gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
                    log.info("good move white queen left bottom corner");
                    gamePlay.setMoveStatus(MoveStatus.OK);
                }else {
                    log.info("bad move white queen left bottom corner");
                    gamePlay.setMoveStatus(MoveStatus.BAD);
                }
                return gamePlay;
            }
        }

        //sprawdzanie ruchu w rog prawy dolny i lewy gorny

        if(Character.getNumericValue(oldF[COLUMN]) + Character.getNumericValue(oldF[ROW]) ==
                Character.getNumericValue(newF[COLUMN]) + Character.getNumericValue(newF[ROW]) ){
            //lewy gorny
            if(oldF[ROW] < newF[ROW] && oldF[COLUMN] > newF[COLUMN]) {
                if(checkSpaceBetween.checkPieceBetweenLeftUpCorner(oldF, newF, gamePlay)){
                    gamePlay.setFigureNameNew(Pieces.WHITEQUEEN.getPiece());
                    gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                    gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
                    log.info("good move white queen left up corner");
                    gamePlay.setMoveStatus(MoveStatus.OK);


                }else {
                    log.info("bad move white queen left up corner");
                    gamePlay.setMoveStatus(MoveStatus.BAD);

                }
                return gamePlay;
            }

            //prawy dolny
            if(oldF[ROW] > newF[ROW]  && oldF[COLUMN] < newF[COLUMN]) {
                if(checkSpaceBetween.checkPieceBetweenRightBottomCorner(oldF, newF, gamePlay)){
                    gamePlay.setFigureNameNew(Pieces.WHITEQUEEN.getPiece());
                    gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                    gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
                    log.info("good move white queen right bottom corner");
                    gamePlay.setMoveStatus(MoveStatus.OK);
                }else {
                    log.info("bad move white queen right botton corner");
                    gamePlay.setMoveStatus(MoveStatus.BAD);

                }
                return gamePlay;
            }
        }

        //sprawdzanie ruchu do przodu

        if(oldF[COLUMN] == newF[COLUMN] && oldF[ROW] < newF[ROW]) {
            if(checkSpaceBetween.checkPieceBetweenUp(oldF, newF, gamePlay)){
                gamePlay.setFigureNameNew(Pieces.WHITEQUEEN.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
                log.info("good move white queen up");
                gamePlay.setMoveStatus(MoveStatus.OK);


            }else {
                log.info("bad move white queen up");
                gamePlay.setMoveStatus(MoveStatus.BAD);


            }
            return gamePlay;
        }

        //sprawdzaniu ruchu w tył

        if(oldF[COLUMN] == newF[COLUMN] && oldF[ROW] > newF[ROW]) {
            if(checkSpaceBetween.checkPieceBetweenDown(oldF, newF, gamePlay)){
                gamePlay.setFigureNameNew(Pieces.WHITEQUEEN.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
                log.info("good move white queen down");
                gamePlay.setMoveStatus(MoveStatus.OK);
            }else {
                log.info("bad move white queen up");
                gamePlay.setMoveStatus(MoveStatus.BAD);
            }
            return gamePlay;
        }

        //sprawdzanie ruchu w prawo

        if(oldF[ROW] == newF[ROW] && oldF[COLUMN] < newF[COLUMN]) {
            if(checkSpaceBetween.checkPieceBetweenRight(oldF, newF, gamePlay)){
                gamePlay.setFigureNameNew(Pieces.WHITEQUEEN.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
                log.info("good move white queen right");
                gamePlay.setMoveStatus(MoveStatus.OK);
            }else {
                log.info("bad move white queen up");
                gamePlay.setMoveStatus(MoveStatus.BAD);

            }
            return gamePlay;
        }

        //sprawdzanie ruchu w lewo

        if(oldF[ROW] == newF[ROW]  && oldF[COLUMN] > newF[COLUMN]) {
            if(checkSpaceBetween.checkPieceBetweenLeft(oldF, newF, gamePlay)){
                gamePlay.setFigureNameNew(Pieces.WHITEQUEEN.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
                log.info("good move white queen left");
                gamePlay.setMoveStatus(MoveStatus.OK);
            }else {
                log.info("bad move white queen up");
                gamePlay.setMoveStatus(MoveStatus.BAD);

            }
            return gamePlay;
        }



        return gamePlay;
    }

    public GamePlay checkMoveBlack(GamePlay gamePlay) {

        char[] oldF = gamePlay.getCoordinateOld().toCharArray();
        char[] newF = gamePlay.getCoordinateNew().toCharArray();

        //sprawdzanie ruchu do prawego gornego rogu i lewego dolnego rogu

        if(Character.getNumericValue(oldF[COLUMN]) - Character.getNumericValue(oldF[ROW]) ==
                Character.getNumericValue(newF[COLUMN]) - Character.getNumericValue(newF[ROW]) ) {
            //prawy gorny
            if(oldF[COLUMN] < newF[COLUMN] && oldF[ROW] < newF[ROW]){
                if(checkSpaceBetween.checkPieceBetweenRightUpCorner(oldF, newF, gamePlay)){
                    gamePlay.setFigureNameNew(Pieces.BLACKQUEEN.getPiece());
                    gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                    gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
                    log.info("good move black queen right corner");
                    gamePlay.setMoveStatus(MoveStatus.OK);
                }else {
                    log.info("bad move black queen right corner");
                    gamePlay.setMoveStatus(MoveStatus.BAD);
                }
                return gamePlay;
            }
            //lewy dolny

            if(oldF[COLUMN] > newF[COLUMN] && oldF[ROW] > newF[ROW]) {
                if(checkSpaceBetween.checkPieceBetweenLeftDownCorner(oldF, newF, gamePlay)){
                    gamePlay.setFigureNameNew(Pieces.BLACKQUEEN.getPiece());
                    gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                    gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
                    log.info("good move black queen left bottom corner");
                    gamePlay.setMoveStatus(MoveStatus.OK);
                }else {
                    log.info("bad move black queen left bottom corner");
                    gamePlay.setMoveStatus(MoveStatus.BAD);
                }
                return gamePlay;
            }
        }

        //sprawdzanie ruchu w rog prawy dolny i lewy gorny

        if(Character.getNumericValue(oldF[COLUMN]) + Character.getNumericValue(oldF[ROW]) ==
                Character.getNumericValue(newF[COLUMN]) + Character.getNumericValue(newF[ROW]) ){
            //lewy gorny
            if(oldF[ROW] < newF[ROW] && oldF[COLUMN] > newF[COLUMN]) {
                if(checkSpaceBetween.checkPieceBetweenLeftUpCorner(oldF, newF, gamePlay)){
                    gamePlay.setFigureNameNew(Pieces.BLACKQUEEN.getPiece());
                    gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                    gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
                    log.info("good move black queen left up corner");
                    gamePlay.setMoveStatus(MoveStatus.OK);
                }else {
                    log.info("bad move black queen left up corner");
                    gamePlay.setMoveStatus(MoveStatus.BAD);

                }
                return gamePlay;
            }

            //prawy dolny
            if(oldF[ROW] > newF[ROW]  && oldF[COLUMN] < newF[COLUMN]) {
                if(checkSpaceBetween.checkPieceBetweenRightBottomCorner(oldF, newF, gamePlay)){
                    gamePlay.setFigureNameNew(Pieces.BLACKQUEEN.getPiece());
                    gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                    gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
                    log.info("good move black queen right bottom corner");
                    gamePlay.setMoveStatus(MoveStatus.OK);
                }else {
                    log.info("bad move black queen right botton corner");
                    gamePlay.setMoveStatus(MoveStatus.BAD);

                }
                return gamePlay;
            }
        }

        //sprawdzanie ruchu do przodu

        if(oldF[COLUMN] == newF[COLUMN] && oldF[ROW] < newF[ROW]) {
            if(checkSpaceBetween.checkPieceBetweenUp(oldF, newF, gamePlay)){
                gamePlay.setFigureNameNew(Pieces.BLACKQUEEN.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
                log.info("good move black queen up");
                gamePlay.setMoveStatus(MoveStatus.OK);
            }else {
                log.info("bad move black queen up");
                gamePlay.setMoveStatus(MoveStatus.BAD);
            }
            return gamePlay;
        }

        //sprawdzaniu ruchu w tył

        if(oldF[COLUMN] == newF[COLUMN] && oldF[ROW] > newF[ROW]) {
            if(checkSpaceBetween.checkPieceBetweenDown(oldF, newF, gamePlay)){
                gamePlay.setFigureNameNew(Pieces.BLACKQUEEN.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
                log.info("good move black queen down");
                gamePlay.setMoveStatus(MoveStatus.OK);

            }else {
                log.info("bad move black queen up");
                gamePlay.setMoveStatus(MoveStatus.BAD);
            }
            return gamePlay;
        }

        //sprawdzanie ruchu w prawo

        if(oldF[ROW] == newF[ROW] && oldF[COLUMN] < newF[COLUMN]) {
            if(checkSpaceBetween.checkPieceBetweenRight(oldF, newF, gamePlay)){
                gamePlay.setFigureNameNew(Pieces.BLACKQUEEN.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
                log.info("good move black queen right");
                gamePlay.setMoveStatus(MoveStatus.OK);

            }else {
                log.info("bad move black queen up");
                gamePlay.setMoveStatus(MoveStatus.BAD);
            }
            return gamePlay;
        }

        //sprawdzanie ruchu w lewo

        if(oldF[ROW] == newF[ROW]  && oldF[COLUMN] > newF[COLUMN]) {
            if(checkSpaceBetween.checkPieceBetweenLeft(oldF, newF, gamePlay)){
                gamePlay.setFigureNameNew(Pieces.BLACKQUEEN.getPiece());
                gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
                log.info("good move black queen left");
                gamePlay.setMoveStatus(MoveStatus.OK);
            }else {
                log.info("bad move black queen up");
                gamePlay.setMoveStatus(MoveStatus.BAD);
            }
            return gamePlay;
        }

        return gamePlay;
    }
}
