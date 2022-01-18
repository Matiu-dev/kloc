package com.test.manytomany.chesspiecerules;

import com.test.manytomany.model.GamePlay;
import com.test.manytomany.model.MoveStatus;
import com.test.manytomany.model.Pieces;
import lombok.extern.slf4j.Slf4j;

// goniec
@Slf4j
public class Knight {

    private static final int COLUMN = 0;
    private static final int ROW = 1;

    public GamePlay checkMoveWhite(GamePlay gamePlay) {

        char[] oldF = gamePlay.getCoordinateOld().toCharArray();
        char[] newF = gamePlay.getCoordinateNew().toCharArray();

        boolean checkUp = (Character.getNumericValue(oldF[COLUMN])-1 == Character.getNumericValue(newF[COLUMN])
                || Character.getNumericValue(oldF[COLUMN]) +1 == Character.getNumericValue(newF[COLUMN]))
                && Character.getNumericValue(oldF[ROW]) +2 == Character.getNumericValue(newF[ROW]);

        boolean checkDown = (Character.getNumericValue(oldF[COLUMN])-1 == Character.getNumericValue(newF[COLUMN])
                || Character.getNumericValue(oldF[COLUMN]) +1 == Character.getNumericValue(newF[COLUMN]))
                && Character.getNumericValue(oldF[ROW]) -2 == Character.getNumericValue(newF[ROW]);

        boolean checkRight = (Character.getNumericValue(oldF[ROW])-1 == Character.getNumericValue(newF[ROW])
                || Character.getNumericValue(oldF[ROW]) +1 == Character.getNumericValue(newF[ROW]))
                && Character.getNumericValue(oldF[COLUMN]) +2 == Character.getNumericValue(newF[COLUMN]);

        boolean checkLeft = (Character.getNumericValue(oldF[ROW])-1 == Character.getNumericValue(newF[ROW])
                || Character.getNumericValue(oldF[ROW]) +1 == Character.getNumericValue(newF[ROW]))
                && Character.getNumericValue(oldF[COLUMN]) -2 == Character.getNumericValue(newF[COLUMN]);

        //bicie
        if (checkUp || checkDown || checkRight || checkLeft) {

            log.info("good move white knight");
            gamePlay.setMoveStatus(MoveStatus.OK);
            gamePlay.setFigureNameNew(Pieces.WHITEKNIGHT.getPiece());
            gamePlay.setFigureNameOld("");

        }  else {
            log.info("bad move white knight");
            gamePlay.setMoveStatus(MoveStatus.BAD);
        }

        return gamePlay;
    }

    public GamePlay checkMoveBlack(GamePlay gamePlay) {
        char[] oldF = gamePlay.getCoordinateOld().toCharArray();
        char[] newF = gamePlay.getCoordinateNew().toCharArray();

        boolean checkUp = (Character.getNumericValue(oldF[COLUMN])-1 == Character.getNumericValue(newF[COLUMN])
                || Character.getNumericValue(oldF[COLUMN]) +1 == Character.getNumericValue(newF[COLUMN]))
                && Character.getNumericValue(oldF[ROW]) +2 == Character.getNumericValue(newF[ROW]);

        boolean checkDown = (Character.getNumericValue(oldF[COLUMN])-1 == Character.getNumericValue(newF[COLUMN])
                || Character.getNumericValue(oldF[COLUMN]) +1 == Character.getNumericValue(newF[COLUMN]))
                && Character.getNumericValue(oldF[ROW]) -2 == Character.getNumericValue(newF[ROW]);

        boolean checkRight = (Character.getNumericValue(oldF[ROW])-1 == Character.getNumericValue(newF[ROW])
                || Character.getNumericValue(oldF[ROW]) +1 == Character.getNumericValue(newF[ROW]))
                && Character.getNumericValue(oldF[COLUMN]) +2 == Character.getNumericValue(newF[COLUMN]);

        boolean checkLeft = (Character.getNumericValue(oldF[ROW])-1 == Character.getNumericValue(newF[ROW])
                || Character.getNumericValue(oldF[ROW]) +1 == Character.getNumericValue(newF[ROW]))
                && Character.getNumericValue(oldF[COLUMN]) -2 == Character.getNumericValue(newF[COLUMN]);


        if (checkUp || checkDown || checkRight || checkLeft) {

            log.info("good move black knight");
            gamePlay.setMoveStatus(MoveStatus.OK);
            gamePlay.setFigureNameNew(Pieces.BLACKKNIGHT.getPiece());
            gamePlay.setFigureNameOld("");

        }  else {
            log.info("bad move black knight");
            gamePlay.setMoveStatus(MoveStatus.BAD);
        }

        return gamePlay;
    }
}
