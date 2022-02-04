package com.test.manytomany.model.connect;

import com.test.manytomany.model.player.Player;

public class ConnectRequest {

    private Long playerId;
    private Long gameId;

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}
