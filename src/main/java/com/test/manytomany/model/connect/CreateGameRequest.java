package com.test.manytomany.model.connect;

import com.test.manytomany.model.player.Player;

import java.util.UUID;

public class CreateGameRequest {

//    private UUID playerId;
    private String login;
    private String gameTime;
    private String additionalTime;
    private String gameType;

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

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }
}
