package com.test.manytomany.chesspiecerules;

import com.test.manytomany.model.GamePlay;
import com.test.manytomany.model.MoveStatus;
import com.test.manytomany.model.Pieces;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Bishop {

    private static final int COLUMN = 0;
    private static final int ROW = 1;
    private CheckSpaceBetween checkSpaceBetween;

    public Bishop(){
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
                    gamePlay.setFigureNameNew(Pieces.WHITEBISHOP.getPiece());
                    gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                    log.info("good move white bishop right corner");
                    gamePlay.setMoveStatus(MoveStatus.OK);
                }else {
                    log.info("bad move white rook right corner");
                    gamePlay.setMoveStatus(MoveStatus.BAD);
                }
                return gamePlay;
            }
            //lewy dolny

            if(oldF[COLUMN] > newF[COLUMN] && oldF[ROW] > newF[ROW]) {
                if(checkSpaceBetween.checkPieceBetweenLeftDownCorner(oldF, newF, gamePlay)){
                    gamePlay.setFigureNameNew(Pieces.WHITEBISHOP.getPiece());
                    gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                    log.info("good move white bishop left bottom corner");
                    gamePlay.setMoveStatus(MoveStatus.OK);
                }else {
                    log.info("bad move white bishop left bottom corner");
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
                    gamePlay.setFigureNameNew(Pieces.WHITEBISHOP.getPiece());
                    gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                    log.info("good move white bishop left up corner");
                    gamePlay.setMoveStatus(MoveStatus.OK);


                }else {
                    log.info("bad move white bishop left up corner");
                    gamePlay.setMoveStatus(MoveStatus.BAD);

                }
                return gamePlay;
            }

            //prawy dolny
            if(oldF[ROW] > newF[ROW]  && oldF[COLUMN] < newF[COLUMN]) {
                if(checkSpaceBetween.checkPieceBetweenRightBottomCorner(oldF, newF, gamePlay)){
                    gamePlay.setFigureNameNew(Pieces.WHITEBISHOP.getPiece());
                    gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                    log.info("good move white bishop right bottom corner");
                    gamePlay.setMoveStatus(MoveStatus.OK);
                }else {
                    log.info("bad move white bishop right botton corner");
                    gamePlay.setMoveStatus(MoveStatus.BAD);

                }
                return gamePlay;
            }
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
                    gamePlay.setFigureNameNew(Pieces.BLACKBISHOP.getPiece());
                    gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                    log.info("good move black bishop right corner");
                    gamePlay.setMoveStatus(MoveStatus.OK);
                }else {
                    log.info("bad move black rook right corner");
                    gamePlay.setMoveStatus(MoveStatus.BAD);
                }
                return gamePlay;
            }
            //lewy dolny

            if(oldF[COLUMN] > newF[COLUMN] && oldF[ROW] > newF[ROW]) {
                if(checkSpaceBetween.checkPieceBetweenLeftDownCorner(oldF, newF, gamePlay)){
                    gamePlay.setFigureNameNew(Pieces.BLACKBISHOP.getPiece());
                    gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                    log.info("good move black bishop left bottom corner");
                    gamePlay.setMoveStatus(MoveStatus.OK);
                }else {
                    log.info("bad move black bishop left bottom corner");
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
                    gamePlay.setFigureNameNew(Pieces.BLACKBISHOP.getPiece());
                    gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                    log.info("good move black bishop left up corner");
                    gamePlay.setMoveStatus(MoveStatus.OK);


                }else {
                    log.info("bad move black bishop left up corner");
                    gamePlay.setMoveStatus(MoveStatus.BAD);

                }
                return gamePlay;
            }

            //prawy dolny
            if(oldF[ROW] > newF[ROW]  && oldF[COLUMN] < newF[COLUMN]) {
                if(checkSpaceBetween.checkPieceBetweenRightBottomCorner(oldF, newF, gamePlay)){
                    gamePlay.setFigureNameNew(Pieces.BLACKBISHOP.getPiece());
                    gamePlay.setFigureNameOld(Pieces.EMPTY.getPiece());

                    log.info("good move black bishop right bottom corner");
                    gamePlay.setMoveStatus(MoveStatus.OK);
                }else {
                    log.info("bad move black bishop right botton corner");
                    gamePlay.setMoveStatus(MoveStatus.BAD);

                }
                return gamePlay;
            }
        }

        return gamePlay;

    }
}
