package com.test.manytomany.model;

import com.test.manytomany.model.PlayerBoard.Team;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

public class Disconnect {

    @Enumerated(EnumType.STRING)
    private GameResult gameResult;

    private String type;

    private UUID gameId;

    @Enumerated(EnumType.STRING)
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
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
}
