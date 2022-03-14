package com.test.manytomany.model.connect;

import com.test.manytomany.model.player.Player;

public class ChatConnectRequest {

    Player player;
    Long gameId;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    @Override
    public String toString() {
        return "ChatConnectRequest{" +
                "player=" + player +
                ", gameId=" + gameId +
                '}';
    }

    public ChatConnectRequest(Player player, Long gameId) {
        this.player = player;
        this.gameId = gameId;
    }
}
