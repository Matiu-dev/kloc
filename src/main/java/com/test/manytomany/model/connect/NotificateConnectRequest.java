package com.test.manytomany.model.connect;

import java.util.UUID;

public class NotificateConnectRequest {

//    private UUID playerId;
    private String type;
    private UUID gameId;
    private UUID boardId;
    private UUID boardIdAdditional;
    private String login;

//    public UUID getPlayerId() {
//        return playerId;
//    }
//
//    public void setPlayerId(UUID playerId) {
//        this.playerId = playerId;
//    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public void setBoardId(UUID boardId) {
        this.boardId = boardId;
    }

    public void setBoardIdAdditional(UUID boardIdAdditional) {
        this.boardIdAdditional = boardIdAdditional;
    }

    public UUID getBoardId() {
        return boardId;
    }

    public UUID getBoardIdAdditional() {
        return boardIdAdditional;
    }
}
