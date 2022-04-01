package com.test.manytomany.model.connect;

import com.test.manytomany.model.PlayerBoard.Color;
import com.test.manytomany.model.PlayerBoard.Team;

public class ConnectResponse {

    private Long gameId;
    private Long playerId;
    private Long chatId;
    private Long boardId;
    private Long boardIdAdditional;
    //aktualny ruch gracza
    private Long playerIdMove;

    private Color color;
    private Team team;

    //time
    private String gameTime;
    private String additionalTime;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getPlayerIdMove() {
        return playerIdMove;
    }

    public void setPlayerIdMove(Long playerIdMove) {
        this.playerIdMove = playerIdMove;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Long getBoardIdAdditional() {
        return boardIdAdditional;
    }

    public void setBoardIdAdditional(Long boardIdAdditional) {
        this.boardIdAdditional = boardIdAdditional;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public ConnectResponse(Long gameId, Long boardId, Long boardIdAdditional, Long playerIdMove, Color color) {
        this.gameId = gameId;
        this.boardId = boardId;
        this.boardIdAdditional = boardIdAdditional;
        this.playerIdMove = playerIdMove;
        this.color = color;
    }

    public ConnectResponse() {

    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public String getAdditionalTime() {
        return additionalTime;
    }

    public void setAdditionalTime(String additionalTime) {
        this.additionalTime = additionalTime;
    }
}
