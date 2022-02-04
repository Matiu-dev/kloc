package com.test.manytomany.chesspiecerules;

import com.test.manytomany.model.GamePlay;
import com.test.manytomany.model.MoveStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Pawn {

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

        //bicie na ukos
        if (oldF[COLUMN] + 1 == newF[COLUMN]
                && oldF[ROW] + 1 == newF[ROW]
                && !gamePlay.getFigureNameNew().isEmpty()||
                oldF[COLUMN] - 1 == newF[COLUMN]
                        && oldF[ROW] + 1 == newF[ROW]
                        && !gamePlay.getFigureNameNew().isEmpty()) {

            gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
            log.info("good move white pawn bias");
            gamePlay.setMoveStatus(MoveStatus.OK);
            gamePlay.setFigureNameNew("♙");
            gamePlay.setFigureNameOld("");

        } else if (oldF[ROW] + 1 == newF[ROW] //      ruch do przodu
                && oldF[COLUMN] == newF[COLUMN]
                && gamePlay.getFigureNameNew().isEmpty()||
                oldF[ROW] + 2 == newF[ROW]
                        && oldF[COLUMN] == newF[COLUMN]
                        && oldF[ROW] == STARTINGROWWHITE
                        && gamePlay.getFigureNameNew().isEmpty()) {

            gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
            log.info("good move white pawn forward");
            gamePlay.setMoveStatus(MoveStatus.OK);
            gamePlay.setFigureNameNew("♙");
            gamePlay.setFigureNameOld("");
        } else {
            log.info("bad move white pawn");
            gamePlay.setMoveStatus(MoveStatus.BAD);
        }

        return gamePlay;
    }

    public GamePlay checkMoveBlack(GamePlay gamePlay) {

        char[] oldF = gamePlay.getCoordinateOld().toCharArray();
        char[] newF = gamePlay.getCoordinateNew().toCharArray();

        //bicie na ukos
        if (oldF[COLUMN] + 1 == newF[COLUMN]
                && oldF[ROW] - 1 == newF[ROW]
                && !gamePlay.getFigureNameNew().isEmpty()
                ||
                oldF[COLUMN] - 1 == newF[COLUMN]
                        && oldF[ROW] - 1 == newF[ROW]
                        && !gamePlay.getFigureNameNew().isEmpty()) {

            gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
            log.info("good move black pawn bias");
            gamePlay.setMoveStatus(MoveStatus.OK);
            gamePlay.setFigureNameNew("♟");
            gamePlay.setFigureNameOld("");
        } else if (oldF[ROW] - 1 == newF[ROW]//ruch do przodu
                && oldF[COLUMN] == newF[COLUMN]
                && gamePlay.getFigureNameNew().isEmpty()||
                oldF[ROW] - 2 == newF[ROW]
                        && oldF[COLUMN] == newF[COLUMN]
                        && oldF[ROW] == STARTINGROWBLACK
                        && gamePlay.getFigureNameNew().isEmpty()) {

            gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
            log.info("good move black pawn forward");
            gamePlay.setMoveStatus(MoveStatus.OK);
            gamePlay.setFigureNameNew("♟");
            gamePlay.setFigureNameOld("");
        } else {
            log.info("bad move black pawn");
            gamePlay.setMoveStatus(MoveStatus.BAD);
        }

        return gamePlay;
    }
}
