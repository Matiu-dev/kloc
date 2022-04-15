package com.test.manytomany.model.connect;

import com.test.manytomany.model.player.Player;

import java.util.UUID;

public class ConnectRequest {

//    private UUID playerId;
    private String login;
    private UUID gameId;

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

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }
}
