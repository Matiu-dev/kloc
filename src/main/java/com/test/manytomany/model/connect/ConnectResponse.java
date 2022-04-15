package com.test.manytomany.model.connect;

import com.test.manytomany.model.PlayerBoard.Color;
import com.test.manytomany.model.PlayerBoard.Team;

import java.util.UUID;

public class ConnectResponse {

    private UUID gameId;
    private UUID playerId;
    private Long chatId;
    private UUID boardId;
    private UUID boardIdAdditional;
    //aktualny ruch gracza
    private UUID playerIdMove;

    private Color color;
    private Color colorSecond;
    private Team team;

    //time
    private String gameTime;
    private String additionalTime;

    //typ ruchu
    private String type;

    //dwuosoowa/cztero
    private String gameType;

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

    public UUID getPlayerIdMove() {
        return playerIdMove;
    }

    public void setPlayerIdMove(UUID playerIdMove) {
        this.playerIdMove = playerIdMove;
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public UUID getBoardId() {
        return boardId;
    }

    public void setBoardId(UUID boardId) {
        this.boardId = boardId;
    }

    public UUID getBoardIdAdditional() {
        return boardIdAdditional;
    }

    public void setBoardIdAdditional(UUID boardIdAdditional) {
        this.boardIdAdditional = boardIdAdditional;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public ConnectResponse(UUID gameId, UUID boardId, UUID boardIdAdditional, UUID playerIdMove, Color color) {
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

    public Color getColorSecond() {
        return colorSecond;
    }

    public void setColorSecond(Color colorSecond) {
        this.colorSecond = colorSecond;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }
}
