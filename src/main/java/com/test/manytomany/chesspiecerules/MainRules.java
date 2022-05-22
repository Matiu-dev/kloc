package com.test.manytomany.chesspiecerules;

import com.test.manytomany.chesspiecerules.ChessMoves.*;
import com.test.manytomany.model.GamePlay.GamePlay;
import com.test.manytomany.model.MoveStatus;
import com.test.manytomany.model.MoveType;
import com.test.manytomany.model.Pieces;
import com.test.manytomany.model.PlayerBoard.Color;

public class MainRules {

    public GamePlay checkRules(GamePlay gamePlay) {
        //----ruchy----------

        //dodanie figury z pola dodatkowego - do rozbudowania TODO
        if(gamePlay.getMoveType().equals(MoveType.RESERVE)){

            if(gamePlay.getFigureNameNew().equals("")){

                //zakaz wstawiania pionka w ostatnim wierszu
                if(gamePlay.getCoordinateNew().toCharArray()[1] == '8' &&
                        gamePlay.getFigureNameOld().equals(Pieces.WHITEPAWN.getPiece()) ||
                        gamePlay.getCoordinateNew().toCharArray()[1] == '1' &&
                                gamePlay.getFigureNameOld().equals(Pieces.BLACKPAWN.getPiece())) {

                    gamePlay.setMoveStatus(MoveStatus.BAD);

                    return gamePlay;
                }

                String letter = "";

                if(gamePlay.getFigureNameOld().equals(Pieces.BLACKKING.getPiece())
                || gamePlay.getFigureNameOld().equals(Pieces.WHITEKING.getPiece())) {
                    letter = "K";
                }

                if(gamePlay.getFigureNameOld().equals(Pieces.BLACKQUEEN.getPiece())
                        || gamePlay.getFigureNameOld().equals(Pieces.WHITEQUEEN.getPiece())) {
                    letter = "H";
                }

                if(gamePlay.getFigureNameOld().equals(Pieces.BLACKBISHOP.getPiece())
                        || gamePlay.getFigureNameOld().equals(Pieces.WHITEBISHOP.getPiece())) {
                    letter = "G";
                }

                if(gamePlay.getFigureNameOld().equals(Pieces.BLACKKNIGHT.getPiece())
                        || gamePlay.getFigureNameOld().equals(Pieces.WHITEKNIGHT.getPiece())) {
                    letter = "S";
                }

                if(gamePlay.getFigureNameOld().equals(Pieces.BLACKROOK.getPiece())
                        || gamePlay.getFigureNameOld().equals(Pieces.WHITEROOK.getPiece())) {
                    letter = "W";
                }

                gamePlay.setAlgebraicNotationFirst(gamePlay.getAlgebraicNotationFirst() +
                        letter +
                        "@" + gamePlay.getCoordinateNew() +";");

                gamePlay.setMoveStatus(MoveStatus.OK);
                gamePlay.setFigureNameNew(gamePlay.getFigureNameOld());
                gamePlay.setFigureNameOld("");

                if(gamePlay.getNextMoveColor().equals(Color.BLACK)) {
                    gamePlay.setNextMoveColor(Color.WHITE);
                }

                if(gamePlay.getNextMoveColor().equals(Color.WHITE)) {
                    gamePlay.setNextMoveColor(Color.BLACK);
                }

                gamePlay.setEnPassantCord("");//dodane

                return gamePlay;
            }
        }

        //biały pionek
        if(gamePlay.getFigureNameOld().equals(Pieces.WHITEPAWN.getPiece())
                && !gamePlay.checkWhite(gamePlay.getFigureNameNew())) {
//            gamePlay = new Pawn().checkMoveWhite(gamePlay);
            return new Pawn().checkMoveWhite(gamePlay);
        }

        //czarny pionek
        if(gamePlay.getFigureNameOld().equals(Pieces.BLACKPAWN.getPiece())
                && !gamePlay.checkBlack(gamePlay.getFigureNameNew())) {
//            gamePlay = new Pawn().checkMoveBlack(gamePlay);
            return new Pawn().checkMoveBlack(gamePlay);
        }

        //biała wieża

        if(gamePlay.getFigureNameOld().equals(Pieces.WHITEROOK.getPiece())
                && !gamePlay.checkWhite(gamePlay.getFigureNameNew())) {
//            gamePlay = new Rook().checkMoveWhite(gamePlay);
            gamePlay.setEnPassantCord("");
            return new Rook().checkMoveWhite(gamePlay);
        }

        //czarna wieza

        if(gamePlay.getFigureNameOld().equals(Pieces.BLACKROOK.getPiece())
                && !gamePlay.checkBlack(gamePlay.getFigureNameNew())) {
//            gamePlay = new Rook().checkMoveBlack(gamePlay);
            gamePlay.setEnPassantCord("");
            return new Rook().checkMoveBlack(gamePlay);
        }

        //bialy goniec

        if(gamePlay.getFigureNameOld().equals(Pieces.WHITEBISHOP.getPiece())
                && !gamePlay.checkWhite(gamePlay.getFigureNameNew())) {
//            gamePlay = new Bishop().checkMoveWhite(gamePlay);
            gamePlay.setEnPassantCord("");
            return new Bishop().checkMoveWhite(gamePlay);
        }

        //czarny goniec

        if(gamePlay.getFigureNameOld().equals(Pieces.BLACKBISHOP.getPiece())
                && !gamePlay.checkBlack(gamePlay.getFigureNameNew())) {
//            gamePlay = new Bishop().checkMoveBlack(gamePlay);
            gamePlay.setEnPassantCord("");
            return new Bishop().checkMoveBlack(gamePlay);
        }

        //bialy hetman

        if(gamePlay.getFigureNameOld().equals(Pieces.WHITEQUEEN.getPiece())
                && !gamePlay.checkWhite(gamePlay.getFigureNameNew())) {
//            gamePlay = new Queen().checkMoveWhite(gamePlay);
            gamePlay.setEnPassantCord("");
            return new Queen().checkMoveWhite(gamePlay);
        }

        //czarny hetman

        if(gamePlay.getFigureNameOld().equals(Pieces.BLACKQUEEN.getPiece())
                && !gamePlay.checkBlack(gamePlay.getFigureNameNew())) {
//            gamePlay = new Queen().checkMoveBlack(gamePlay);\
            gamePlay.setEnPassantCord("");
            return new Queen().checkMoveBlack(gamePlay);
        }

        //bialy skoczek

        if(gamePlay.getFigureNameOld().equals(Pieces.WHITEKNIGHT.getPiece())
                && !gamePlay.checkWhite(gamePlay.getFigureNameNew())) {
//            gamePlay = new Knight().checkMoveWhite(gamePlay);
            gamePlay.setEnPassantCord("");
            return new Knight().checkMoveWhite(gamePlay);
        }

        //czarny skoczek

        if(gamePlay.getFigureNameOld().equals(Pieces.BLACKKNIGHT.getPiece())
                && !gamePlay.checkBlack(gamePlay.getFigureNameNew())) {
//            gamePlay = new Knight().checkMoveBlack(gamePlay);
            gamePlay.setEnPassantCord("");
            return new Knight().checkMoveBlack(gamePlay);
        }

        //biały krol

        if(gamePlay.getFigureNameOld().equals(Pieces.WHITEKING.getPiece())
                && !gamePlay.checkWhite(gamePlay.getFigureNameNew())) {
//            gamePlay = new King().checkMoveWhite(gamePlay);
            gamePlay.setEnPassantCord("");
            return new King().checkMoveWhite(gamePlay);
        }

        //czarny krol

        if(gamePlay.getFigureNameOld().equals(Pieces.BLACKKING.getPiece())
                && !gamePlay.checkBlack(gamePlay.getFigureNameNew())) {
//            gamePlay =  new King().checkMoveBlack(gamePlay);
            gamePlay.setEnPassantCord("");
            return new King().checkMoveBlack(gamePlay);
        }

        //roszada - dla wiezy lub króla białego

        if(gamePlay.getFigureNameOld().equals(Pieces.WHITEKING.getPiece())
                && gamePlay.getFigureNameNew().equals(Pieces.WHITEROOK.getPiece())
                || (gamePlay.getFigureNameOld().equals(Pieces.WHITEROOK.getPiece())
                && gamePlay.getFigureNameNew().equals(Pieces.WHITEKING.getPiece()))) {
            gamePlay.setEnPassantCord("");
            return new Castling().checkMoveWhite(gamePlay);
        }

        //roszada - dla wiezy lub króla czarnego

        if(gamePlay.getFigureNameOld().equals(Pieces.BLACKKING.getPiece())
                && gamePlay.getFigureNameNew().equals(Pieces.BLACKROOK.getPiece())
                || gamePlay.getFigureNameOld().equals(Pieces.BLACKROOK.getPiece())
                && gamePlay.getFigureNameNew().equals(Pieces.BLACKKING.getPiece())) {
            gamePlay.setEnPassantCord("");
            return new Castling().checkMoveBlack(gamePlay);
        }

        return gamePlay;
    }
}
