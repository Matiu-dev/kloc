package com.test.manytomany.model;

import com.test.manytomany.model.PlayerBoard.Color;
import com.test.manytomany.model.player.PlayerRole;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class GamePlay {

    private String gameId;

    private String boardId;
    private String playerId;

    //nazwa figury i koordynaty stare
    private String coordinateOld;
    private String figureNameOld;

    //nazwa figury i koordynaty nowe
    private String coordinateNew;
    private String figureNameNew;

    //status ruchu po sprawdzeniu czy nic nie blokuje przejscia
    @Enumerated(EnumType.STRING)
    private MoveStatus moveStatus;

    //status planszy czyli czy zakonczono czy jest w trakcie
    private boolean boardStatus;

    //figury na szachownicy
    private String[][] figuresOnBoard;

    private Color nextMoveColor;

    public Color getNextMoveColor() {
        return nextMoveColor;
    }

    public void setNextMoveColor(Color nextMoveColor) {
        this.nextMoveColor = nextMoveColor;
    }

    private String boardName;//A B - do usuniecia pewnie bo to na froncie jest sprawdzane

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public MoveStatus getMoveStatus() {
        return moveStatus;
    }

    public void setMoveStatus(MoveStatus moveStatus) {
        this.moveStatus = moveStatus;
    }

    public String getBoardId() {
        return boardId;
    }

    public String[][] getFiguresOnBoard() {
        return figuresOnBoard;
    }

    public void setFiguresOnBoard(String[][] figuresOnBoard) {
        this.figuresOnBoard = figuresOnBoard;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getCoordinateOld() {
        return coordinateOld;
    }

    public void setCoordinateOld(String coordinateOld) {
        this.coordinateOld = coordinateOld;
    }

    public String getFigureNameOld() {
        return figureNameOld;
    }

    public void setFigureNameOld(String figureNameOld) {
        this.figureNameOld = figureNameOld;
    }

    public String getCoordinateNew() {
        return coordinateNew;
    }

    public void setCoordinateNew(String coordinateNew) {
        this.coordinateNew = coordinateNew;
    }

    public String getFigureNameNew() {
        return figureNameNew;
    }

    public void setFigureNameNew(String figureNameNew) {
        this.figureNameNew = figureNameNew;
    }

    public boolean isBoardStatus() {
        return boardStatus;
    }

    public void setBoardStatus(boolean boardStatus) {
        this.boardStatus = boardStatus;
    }

    public boolean checkWhite(String piece) {
        if(piece.equals(Pieces.WHITEPAWN.getPiece())
                || piece.equals(Pieces.WHITEROOK.getPiece())
                || piece.equals(Pieces.WHITEBISHOP.getPiece())
                || piece.equals(Pieces.WHITEKING.getPiece())
                || piece.equals(Pieces.WHITEQUEEN.getPiece())
                || piece.equals(Pieces.WHITEKNIGHT.getPiece())){
            return true;
        }else {
            return false;
        }
    }

    public boolean checkBlack(String piece) {
        if(piece.equals(Pieces.BLACKPAWN.getPiece())
                || piece.equals(Pieces.BLACKROOK.getPiece())
                || piece.equals(Pieces.BLACKBISHOP.getPiece())
                || piece.equals(Pieces.BLACKKING.getPiece())
                || piece.equals(Pieces.BLACKQUEEN.getPiece())
                || piece.equals(Pieces.BLACKKNIGHT.getPiece())){
            return true;
        }else {
            return false;
        }
    }
}
