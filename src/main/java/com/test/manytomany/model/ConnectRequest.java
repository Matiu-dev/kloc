package com.test.manytomany.model;

import com.test.manytomany.model.player.Player;

public class ConnectRequest {
    private Long playerId;
    private Long boardId;

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }
}
