package com.test.manytomany.model;

public class GamePlay {

    private String boardId;
    private String playerId;

    private String coordinateOld;
    private String figureNameOld;

    private String coordinateNew;
    private String figureNameNew;

    private boolean boardStatus;

    public String getBoardId() {
        return boardId;
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
}
