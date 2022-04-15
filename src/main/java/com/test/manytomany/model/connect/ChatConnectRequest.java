package com.test.manytomany.model.connect;

import com.test.manytomany.model.player.Player;

import java.util.UUID;

public class ChatConnectRequest {

    private Player player;
    private UUID gameId;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    @Override
    public String toString() {
        return "ChatConnectRequest{" +
                "player=" + player +
                ", gameId=" + gameId +
                '}';
    }

    public ChatConnectRequest(Player player, UUID gameId) {
        this.player = player;
        this.gameId = gameId;
    }
}
