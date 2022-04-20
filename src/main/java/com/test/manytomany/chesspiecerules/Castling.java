package com.test.manytomany.chesspiecerules;

import com.test.manytomany.model.GamePlay;
import com.test.manytomany.model.MoveStatus;
import com.test.manytomany.model.Pieces;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Castling {

    private static final String WHITE_KING_START_POSITION = "51";
    private static final String WHITE_ROOK_LEFT_START_POSITION = "11";
    private static final String WHITE_ROOK_RIGHT_START_POSITION = "81";

    private static final String BLACK_KING_START_POSITION = "58";
    private static final String BLACK_ROOK_LEFT_START_POSITION = "18";
    private static final String BLACK_ROOK_RIGHT_START_POSITION = "88";

    private CheckSpaceBetween checkSpaceBetween;

    public Castling() {
        checkSpaceBetween = new CheckSpaceBetween();
    }

    public GamePlay checkMoveWhite(GamePlay gamePlay) {

//        Zasada 1. Nie możesz zrobić roszady, jeśli ruszałeś się już królem!
        boolean ruleOneLeft =
                gamePlay.getCoordinateOld().equals(WHITE_KING_START_POSITION)
                        && gamePlay.getFigureNameOld().equals(Pieces.WHITEKING.getPiece())
                        && gamePlay.getCoordinateNew().equals(WHITE_ROOK_LEFT_START_POSITION)
                        && gamePlay.getFigureNameNew().equals(Pieces.WHITEROOK.getPiece())
                        && gamePlay.getCastling()[0] && gamePlay.getCastling()[1] ||
                        gamePlay.getCoordinateOld().equals(WHITE_ROOK_LEFT_START_POSITION)
                        && gamePlay.getFigureNameOld().equals(Pieces.WHITEROOK.getPiece())
                        && gamePlay.getCoordinateNew().equals(WHITE_KING_START_POSITION)
                        && gamePlay.getFigureNameNew().equals(Pieces.WHITEKING.getPiece())
                        && gamePlay.getCastling()[0] && gamePlay.getCastling()[1];
        //najpierw kliknieto wieze potem krola
        boolean ruleOneRight =
                gamePlay.getCoordinateOld().equals(WHITE_KING_START_POSITION)
                        && gamePlay.getFigureNameOld().equals(Pieces.WHITEKING.getPiece())
                        && gamePlay.getCoordinateNew().equals(WHITE_ROOK_RIGHT_START_POSITION)
                        && gamePlay.getFigureNameNew().equals(Pieces.WHITEROOK.getPiece())
                        && gamePlay.getCastling()[0] && gamePlay.getCastling()[2]
                        || gamePlay.getCoordinateOld().equals(WHITE_ROOK_RIGHT_START_POSITION)
                        && gamePlay.getFigureNameOld().equals(Pieces.WHITEROOK.getPiece())
                        && gamePlay.getCoordinateNew().equals(WHITE_KING_START_POSITION)
                        && gamePlay.getFigureNameNew().equals(Pieces.WHITEKING.getPiece())
                        && gamePlay.getCastling()[0] && gamePlay.getCastling()[2];

//        Zasada 2. Nie można za pomocą roszady uciekać przed szachem!
        boolean ruleTwo = new CheckAttack().checkAttackOnWhiteKingByBlackFigures(
                WHITE_KING_START_POSITION,
                gamePlay.getFiguresOnBoard(),
                gamePlay.getCoordinateOld(),
                gamePlay.getFigureNameOld(),
                gamePlay.getCoordinateNew(),
                gamePlay.getFigureNameNew(),
                gamePlay.getNextMoveColor());

        //Zasada 3. Nie można robić roszady, przechodząc królem przez atakowane pole!
        boolean ruleThreeLeft = false;
        boolean ruleThreeRight = false;

        if ((gamePlay.getCoordinateOld().equals(WHITE_ROOK_LEFT_START_POSITION)
                && gamePlay.getCoordinateNew().equals(WHITE_KING_START_POSITION)
                || gamePlay.getCoordinateOld().equals(WHITE_KING_START_POSITION)
                && gamePlay.getCoordinateNew().equals(WHITE_ROOK_LEFT_START_POSITION))
//        && new CheckAttack().checkAttackOnWhiteKingByBlackFigures(gamePlay, "21")
                && new CheckAttack().checkAttackOnWhiteKingByBlackFigures(
                "31",
                gamePlay.getFiguresOnBoard(),
                gamePlay.getCoordinateOld(),
                gamePlay.getFigureNameOld(),
                gamePlay.getCoordinateNew(),
                gamePlay.getFigureNameNew(),
                gamePlay.getNextMoveColor())
                && new CheckAttack().checkAttackOnWhiteKingByBlackFigures(
                "41",
                gamePlay.getFiguresOnBoard(),
                gamePlay.getCoordinateOld(),
                gamePlay.getFigureNameOld(),
                gamePlay.getCoordinateNew(),
                gamePlay.getFigureNameNew(),
                gamePlay.getNextMoveColor())) {
            ruleThreeLeft = true;
        }

        if ((gamePlay.getCoordinateOld().equals(WHITE_ROOK_RIGHT_START_POSITION)
                && gamePlay.getCoordinateNew().equals(WHITE_KING_START_POSITION)
                || gamePlay.getCoordinateOld().equals(WHITE_KING_START_POSITION)
                && gamePlay.getCoordinateNew().equals(WHITE_ROOK_RIGHT_START_POSITION))
//        && new CheckAttack().checkAttackOnWhiteKingByBlackFigures(gamePlay, "21")
                && new CheckAttack().checkAttackOnWhiteKingByBlackFigures(
                "61",
                gamePlay.getFiguresOnBoard(),
                gamePlay.getCoordinateOld(),
                gamePlay.getFigureNameOld(),
                gamePlay.getCoordinateNew(),
                gamePlay.getFigureNameNew(),
                gamePlay.getNextMoveColor())
                && new CheckAttack().checkAttackOnWhiteKingByBlackFigures(
                "71",
                gamePlay.getFiguresOnBoard(),
                gamePlay.getCoordinateOld(),
                gamePlay.getFigureNameOld(),
                gamePlay.getCoordinateNew(),
                gamePlay.getFigureNameNew(),
                gamePlay.getNextMoveColor())) {
            ruleThreeRight = true;
        }

//        Zasada 4. Pomiędzy królem i wieżą nie może stać żadna figura
        boolean ruleFourRight = false;
        boolean ruleFourLeft = false;

        if ((gamePlay.getCoordinateOld().equals(WHITE_ROOK_LEFT_START_POSITION)
                && gamePlay.getCoordinateNew().equals(WHITE_KING_START_POSITION)
                || gamePlay.getCoordinateOld().equals(WHITE_KING_START_POSITION)
                && gamePlay.getCoordinateNew().equals(WHITE_ROOK_LEFT_START_POSITION))
                && gamePlay.getFiguresOnBoard()[2][1].equals("")
                && gamePlay.getFiguresOnBoard()[3][1].equals("")
                && gamePlay.getFiguresOnBoard()[4][1].equals("")) {
            ruleFourLeft = true;
        }

        if ((gamePlay.getCoordinateOld().equals(WHITE_ROOK_RIGHT_START_POSITION)
                && gamePlay.getCoordinateNew().equals(WHITE_KING_START_POSITION)
                || gamePlay.getCoordinateOld().equals(WHITE_KING_START_POSITION)
                && gamePlay.getCoordinateNew().equals(WHITE_ROOK_RIGHT_START_POSITION))
                && gamePlay.getFiguresOnBoard()[6][1].equals("")
                && gamePlay.getFiguresOnBoard()[7][1].equals("")) {
            ruleFourRight = true;
        }

         if (ruleOneLeft && ruleTwo && ruleThreeLeft && ruleFourLeft) {

             //roszada lewo
             System.out.println("lewo roszada " + ruleOneLeft + " " + ruleTwo + " " + ruleThreeLeft + " " + ruleFourLeft);


             gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
            log.info("good move castling");
            gamePlay.setMoveStatus(MoveStatus.OK);

            boolean[] castling = new boolean[3];
            castling[0] = false;
            castling[1] = false;
            castling[2] = false;
            gamePlay.setCastling(castling);

            gamePlay.setFigureNameNew(Pieces.WHITEKING.getPiece());
            gamePlay.setCoordinateNew("31");

            gamePlay.setFigureNameOld(Pieces.WHITEROOK.getPiece());
            gamePlay.setCoordinateOld("41");

            gamePlay.setCastlingMove(true);
        } else if (ruleOneRight && ruleTwo && ruleThreeRight && ruleFourRight) {

             //roszada prawo
             System.out.println("prawo roszada " + ruleOneRight + " " + ruleTwo + " " + ruleThreeRight + " " + ruleFourRight);


             gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
            log.info("good move castling");
            gamePlay.setMoveStatus(MoveStatus.OK);

            boolean[] castling = new boolean[3];
            castling[0] = false;
            castling[1] = false;
            castling[2] = false;
            gamePlay.setCastling(castling);

            gamePlay.setFigureNameNew(Pieces.WHITEKING.getPiece());
            gamePlay.setCoordinateNew("71");

            gamePlay.setFigureNameOld(Pieces.WHITEROOK.getPiece());
            gamePlay.setCoordinateOld("61");

            gamePlay.setCastlingMove(true);
        } else {
            log.info("bad move castling");
            gamePlay.setMoveStatus(MoveStatus.BAD);
        }

        return gamePlay;
    }

    public GamePlay checkMoveBlack(GamePlay gamePlay) {
        //        Zasada 1. Nie możesz zrobić roszady, jeśli ruszałeś się już królem!
        boolean ruleOneLeft =
                gamePlay.getCoordinateOld().equals(BLACK_KING_START_POSITION)
                        && gamePlay.getFigureNameOld().equals(Pieces.BLACKKING.getPiece())
                        && gamePlay.getCoordinateNew().equals(BLACK_ROOK_LEFT_START_POSITION)
                        && gamePlay.getFigureNameNew().equals(Pieces.BLACKROOK.getPiece())
                        && gamePlay.getCastling()[0] && gamePlay.getCastling()[1] ||
                        gamePlay.getCoordinateOld().equals(BLACK_ROOK_LEFT_START_POSITION)
                                && gamePlay.getFigureNameOld().equals(Pieces.BLACKROOK.getPiece())
                                && gamePlay.getCoordinateNew().equals(BLACK_KING_START_POSITION)
                                && gamePlay.getFigureNameNew().equals(Pieces.BLACKKING.getPiece())
                                && gamePlay.getCastling()[0] && gamePlay.getCastling()[1];
        //najpierw kliknieto wieze potem krola
        boolean ruleOneRight =
                gamePlay.getCoordinateOld().equals(BLACK_KING_START_POSITION)
                        && gamePlay.getFigureNameOld().equals(Pieces.BLACKKING.getPiece())
                        && gamePlay.getCoordinateNew().equals(BLACK_ROOK_RIGHT_START_POSITION)
                        && gamePlay.getFigureNameNew().equals(Pieces.BLACKROOK.getPiece())
                        && gamePlay.getCastling()[0] && gamePlay.getCastling()[2]
                        || gamePlay.getCoordinateOld().equals(BLACK_ROOK_RIGHT_START_POSITION)
                        && gamePlay.getFigureNameOld().equals(Pieces.BLACKROOK.getPiece())
                        && gamePlay.getCoordinateNew().equals(BLACK_KING_START_POSITION)
                        && gamePlay.getFigureNameNew().equals(Pieces.BLACKKING.getPiece())
                        && gamePlay.getCastling()[0] && gamePlay.getCastling()[2];

        //Zasada 2. Nie można za pomocą roszady uciekać przed szachem!
        boolean ruleTwo = new CheckAttack().checkAttackOnBlackKingByWhiteFigures(
                BLACK_KING_START_POSITION,
                gamePlay.getFiguresOnBoard(),
                gamePlay.getCoordinateOld(),
                gamePlay.getFigureNameOld(),
                gamePlay.getCoordinateNew(),
                gamePlay.getFigureNameNew(),
                gamePlay.getNextMoveColor());

        //Zasada 3. Nie można robić roszady, przechodząc królem przez atakowane pole!
        boolean ruleThreeLeft = false;
        boolean ruleThreeRight = false;

        if ((gamePlay.getCoordinateOld().equals(BLACK_ROOK_LEFT_START_POSITION)
                && gamePlay.getCoordinateNew().equals(BLACK_KING_START_POSITION)
                || gamePlay.getCoordinateOld().equals(BLACK_KING_START_POSITION)
                && gamePlay.getCoordinateNew().equals(BLACK_ROOK_LEFT_START_POSITION))
//        && new CheckAttack().checkAttackOnWhiteKingByBlackFigures(gamePlay, "21")
                && new CheckAttack().checkAttackOnBlackKingByWhiteFigures(
                "38",
                gamePlay.getFiguresOnBoard(),
                gamePlay.getCoordinateOld(),
                gamePlay.getFigureNameOld(),
                gamePlay.getCoordinateNew(),
                gamePlay.getFigureNameNew(),
                gamePlay.getNextMoveColor())
                && new CheckAttack().checkAttackOnBlackKingByWhiteFigures(
                "48",
                gamePlay.getFiguresOnBoard(),
                gamePlay.getCoordinateOld(),
                gamePlay.getFigureNameOld(),
                gamePlay.getCoordinateNew(),
                gamePlay.getFigureNameNew(),
                gamePlay.getNextMoveColor())) {
            ruleThreeLeft = true;
        }

        if ((gamePlay.getCoordinateOld().equals(BLACK_ROOK_RIGHT_START_POSITION)
                && gamePlay.getCoordinateNew().equals(BLACK_KING_START_POSITION)
                || gamePlay.getCoordinateOld().equals(BLACK_KING_START_POSITION)
                && gamePlay.getCoordinateNew().equals(BLACK_ROOK_RIGHT_START_POSITION))
//        && new CheckAttack().checkAttackOnWhiteKingByBlackFigures(gamePlay, "21")
                && new CheckAttack().checkAttackOnBlackKingByWhiteFigures(
                "68",
                gamePlay.getFiguresOnBoard(),
                gamePlay.getCoordinateOld(),
                gamePlay.getFigureNameOld(),
                gamePlay.getCoordinateNew(),
                gamePlay.getFigureNameNew(),
                gamePlay.getNextMoveColor())
                && new CheckAttack().checkAttackOnBlackKingByWhiteFigures(
                "78",
                gamePlay.getFiguresOnBoard(),
                gamePlay.getCoordinateOld(),
                gamePlay.getFigureNameOld(),
                gamePlay.getCoordinateNew(),
                gamePlay.getFigureNameNew(),
                gamePlay.getNextMoveColor())) {
            ruleThreeRight = true;
        }

//        Zasada 4. Pomiędzy królem i wieżą nie może stać żadna figura
        boolean ruleFourRight = false;
        boolean ruleFourLeft = false;

        if ((gamePlay.getCoordinateOld().equals(BLACK_ROOK_LEFT_START_POSITION)
                && gamePlay.getCoordinateNew().equals(BLACK_KING_START_POSITION)
                || gamePlay.getCoordinateOld().equals(BLACK_KING_START_POSITION)
                && gamePlay.getCoordinateNew().equals(BLACK_ROOK_LEFT_START_POSITION))
                && gamePlay.getFiguresOnBoard()[2][8].equals("")
                && gamePlay.getFiguresOnBoard()[3][8].equals("")
                && gamePlay.getFiguresOnBoard()[4][8].equals("")) {
            ruleFourLeft = true;
        }

        if ((gamePlay.getCoordinateOld().equals(BLACK_ROOK_RIGHT_START_POSITION)
                && gamePlay.getCoordinateNew().equals(BLACK_KING_START_POSITION)
                || gamePlay.getCoordinateOld().equals(BLACK_KING_START_POSITION)
                && gamePlay.getCoordinateNew().equals(BLACK_ROOK_RIGHT_START_POSITION))
                && gamePlay.getFiguresOnBoard()[6][8].equals("")
                && gamePlay.getFiguresOnBoard()[7][8].equals("")) {
            ruleFourRight = true;
        }


        if (ruleOneLeft && ruleTwo && ruleThreeLeft && ruleFourLeft) {

            //roszada lewo
            System.out.println("lewo roszada " + ruleOneLeft + " " + ruleTwo + " " + ruleThreeLeft + " " + ruleFourLeft);


            gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
            log.info("good move black castling");
            gamePlay.setMoveStatus(MoveStatus.OK);

            boolean[] castling = new boolean[3];
            castling[0] = false;
            castling[1] = false;
            castling[2] = false;
            gamePlay.setCastling(castling);

            gamePlay.setFigureNameNew(Pieces.BLACKKING.getPiece());
            gamePlay.setCoordinateNew("38");

            gamePlay.setFigureNameOld(Pieces.BLACKROOK.getPiece());
            gamePlay.setCoordinateOld("48");

            gamePlay.setCastlingMove(true);
        } else if (ruleOneRight && ruleTwo && ruleThreeRight && ruleFourRight) {

            //roszada prawo
            System.out.println("prawo roszada " + ruleOneRight + " " + ruleTwo + " " + ruleThreeRight + " " + ruleFourRight);


            gamePlay.setNextMoveColor(checkSpaceBetween.changeTextMoveColor(gamePlay));
            log.info("good move black castling");
            gamePlay.setMoveStatus(MoveStatus.OK);

            boolean[] castling = new boolean[3];
            castling[0] = false;
            castling[1] = false;
            castling[2] = false;
            gamePlay.setCastling(castling);

            gamePlay.setFigureNameNew(Pieces.BLACKKING.getPiece());
            gamePlay.setCoordinateNew("78");

            gamePlay.setFigureNameOld(Pieces.BLACKROOK.getPiece());
            gamePlay.setCoordinateOld("68");

            gamePlay.setCastlingMove(true);
        } else {
            log.info("bad move castling");
            gamePlay.setMoveStatus(MoveStatus.BAD);
        }

        return gamePlay;
    }
}
