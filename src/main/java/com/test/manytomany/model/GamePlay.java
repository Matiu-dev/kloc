package com.test.manytomany.model;

import com.test.manytomany.model.PlayerBoard.Color;
import com.test.manytomany.model.PlayerBoard.Team;
import com.test.manytomany.model.game.WinnerTeam;

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

    //typ ruchu np ruch pionka/zbicie , dodanie figury z rezerwy
    @Enumerated(EnumType.STRING)
    private MoveType moveType;

    //status planszy czyli czy jesz szachmat
    @Enumerated(EnumType.STRING)
    private GameResult gameResult;

    @Enumerated(EnumType.STRING)
    private Team team;

    //figury na szachownicy
    private String[][] figuresOnBoard;

    private Color nextMoveColor;

    //roszada krol/lewa wieza/prawa wieza
    private boolean[] castling;

    private boolean castlingMove;

    //bicie w przelocie
    private String enPassantCord;

    private boolean enPassantMove;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //    do promocji pionka
    private String promoFigure;

    public String getPromoFigure() {
        return promoFigure;
    }

    public void setPromoFigure(String promoFigure) {
        this.promoFigure = promoFigure;
    }

    public boolean isEnPassantMove() {
        return enPassantMove;
    }

    public void setEnPassantMove(boolean enPassantMove) {
        this.enPassantMove = enPassantMove;
    }

    public String getEnPassantCord() {
        return enPassantCord;
    }

    public void setEnPassantCord(String enPassantCord) {
        this.enPassantCord = enPassantCord;
    }

    public boolean isCastlingMove() {
        return castlingMove;
    }

    public void setCastlingMove(boolean castlingMove) {
        this.castlingMove = castlingMove;
    }

    public boolean[] getCastling() {
        return castling;
    }

    public void setCastling(boolean[] castling) {
        this.castling = castling;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public void setMoveType(MoveType moveType) {
        this.moveType = moveType;
    }

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

    public GameResult getGameResult() {
        return gameResult;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
