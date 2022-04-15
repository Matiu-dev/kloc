package com.test.manytomany.model.PlayerBoard;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class PlayerBoardId implements Serializable {

    @Column(name="player_id")
    private UUID playerId;

    @Column(name="board_id")
    private UUID boardId;

    public PlayerBoardId(UUID playerId, UUID boardId) {
        this.playerId = playerId;
        this.boardId = boardId;
    }

    public PlayerBoardId(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerBoardId that = (PlayerBoardId) o;

        if (playerId != null ? !playerId.equals(that.playerId) : that.playerId != null) return false;
        return boardId != null ? boardId.equals(that.boardId) : that.boardId == null;
    }

    @Override
    public int hashCode() {
        int result = playerId != null ? playerId.hashCode() : 0;
        result = 31 * result + (boardId != null ? boardId.hashCode() : 0);
        return result;
    }
}
