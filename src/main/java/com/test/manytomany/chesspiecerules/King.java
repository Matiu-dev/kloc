package com.test.manytomany.chesspiecerules;

import com.test.manytomany.model.GamePlay;
import com.test.manytomany.model.MoveStatus;
import com.test.manytomany.model.Pieces;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class King {

    private static final int COLUMN = 0;
    private static final int ROW = 1;

    public GamePlay checkMoveWhite(GamePlay gamePlay) {

        char[] oldF = gamePlay.getCoordinateOld().toCharArray();
        char[] newF = gamePlay.getCoordinateNew().toCharArray();

        boolean checkUp = Character.getNumericValue(oldF[COLUMN]) == Character.getNumericValue(newF[COLUMN])
                && Character.getNumericValue(oldF[ROW]) +1 == Character.getNumericValue(newF[ROW]);

        boolean checkDown = Character.getNumericValue(oldF[COLUMN]) == Character.getNumericValue(newF[COLUMN])
                && Character.getNumericValue(oldF[ROW]) - 1 == Character.getNumericValue(newF[ROW]);

        boolean checkRight = Character.getNumericValue(oldF[COLUMN]) +1 == Character.getNumericValue(newF[COLUMN])
                && Character.getNumericValue(oldF[ROW]) == Character.getNumericValue(newF[ROW]);

        boolean checkLeft = Character.getNumericValue(oldF[COLUMN]) - 1 == Character.getNumericValue(newF[COLUMN])
                && Character.getNumericValue(oldF[ROW]) == Character.getNumericValue(newF[ROW]);

        boolean checkUpRight = Character.getNumericValue(oldF[COLUMN]) +1 == Character.getNumericValue(newF[COLUMN])
                && Character.getNumericValue(oldF[ROW]) +1 == Character.getNumericValue(newF[ROW]);

        boolean checkUpLeft = Character.getNumericValue(oldF[COLUMN]) -1 == Character.getNumericValue(newF[COLUMN])
                && Character.getNumericValue(oldF[ROW]) + 1 == Character.getNumericValue(newF[ROW]);

        boolean checkDownRight = Character.getNumericValue(oldF[COLUMN]) +1 == Character.getNumericValue(newF[COLUMN])
                && Character.getNumericValue(oldF[ROW]) - 1 == Character.getNumericValue(newF[ROW]);

        boolean checkDownLeft = Character.getNumericValue(oldF[COLUMN]) - 1 == Character.getNumericValue(newF[COLUMN])
                && Character.getNumericValue(oldF[ROW]) -1 == Character.getNumericValue(newF[ROW]);



        if (checkUp || checkDown || checkRight || checkLeft
        || checkUpRight || checkUpLeft || checkDownRight || checkDownLeft) {

            log.info("good move white king");
            gamePlay.setMoveStatus(MoveStatus.OK);
            gamePlay.setFigureNameNew(Pieces.WHITEKING.getPiece());
            gamePlay.setFigureNameOld("");

        }  else {
            log.info("bad move white king");
            gamePlay.setMoveStatus(MoveStatus.BAD);
        }

        return gamePlay;
    }

    public GamePlay checkMoveBlack(GamePlay gamePlay) {
        char[] oldF = gamePlay.getCoordinateOld().toCharArray();
        char[] newF = gamePlay.getCoordinateNew().toCharArray();

        boolean checkUp = Character.getNumericValue(oldF[COLUMN]) == Character.getNumericValue(newF[COLUMN])
                && Character.getNumericValue(oldF[ROW]) + 1 == Character.getNumericValue(newF[ROW]);

        boolean checkDown = Character.getNumericValue(oldF[COLUMN]) == Character.getNumericValue(newF[COLUMN])
                && Character.getNumericValue(oldF[ROW]) - 1 == Character.getNumericValue(newF[ROW]);

        boolean checkRight = Character.getNumericValue(oldF[COLUMN]) + 1 == Character.getNumericValue(newF[COLUMN])
                && Character.getNumericValue(oldF[ROW]) == Character.getNumericValue(newF[ROW]);

        boolean checkLeft = Character.getNumericValue(oldF[COLUMN]) - 1 == Character.getNumericValue(newF[COLUMN])
                && Character.getNumericValue(oldF[ROW]) == Character.getNumericValue(newF[ROW]);

        boolean checkUpRight = Character.getNumericValue(oldF[COLUMN]) + 1 == Character.getNumericValue(newF[COLUMN])
                && Character.getNumericValue(oldF[ROW]) + 1 == Character.getNumericValue(newF[ROW]);

        boolean checkUpLeft = Character.getNumericValue(oldF[COLUMN]) - 1 == Character.getNumericValue(newF[COLUMN])
                && Character.getNumericValue(oldF[ROW]) + 1 == Character.getNumericValue(newF[ROW]);

        boolean checkDownRight = Character.getNumericValue(oldF[COLUMN]) + 1 == Character.getNumericValue(newF[COLUMN])
                && Character.getNumericValue(oldF[ROW]) - 1 == Character.getNumericValue(newF[ROW]);

        boolean checkDownLeft = Character.getNumericValue(oldF[COLUMN]) - 1 == Character.getNumericValue(newF[COLUMN])
                && Character.getNumericValue(oldF[ROW]) - 1 == Character.getNumericValue(newF[ROW]);


        if (checkUp || checkDown || checkRight || checkLeft
                || checkUpRight || checkUpLeft || checkDownRight || checkDownLeft) {

            log.info("good move black king");
            gamePlay.setMoveStatus(MoveStatus.OK);
            gamePlay.setFigureNameNew(Pieces.BLACKKING.getPiece());
            gamePlay.setFigureNameOld("");

        } else {
            log.info("bad move black king");
            gamePlay.setMoveStatus(MoveStatus.BAD);
        }

        return gamePlay;
    }
}
