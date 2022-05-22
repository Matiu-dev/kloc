package com.test.manytomany.model.history;

import com.test.manytomany.model.game.GameStatus;
import com.test.manytomany.model.game.WinnerTeam;

import java.util.UUID;

public class PlayerGameHistory {

    private UUID gameId;
    private String gameType;
    private WinnerTeam winnerTeam;
    private String gameTime;
    private String additionalTime;
    private GameStatus gameStatus;
    private String moveHistoryFirst;
    private String moveHistorySecond;

    public PlayerGameHistory() {

    }

    public PlayerGameHistory(UUID gameId, String gameType, WinnerTeam winnerTeam, String gameTime, String additionalTime, GameStatus gameStatus, String moveHistoryFirst, String moveHistorySecond) {
        this.gameId = gameId;
        this.gameType = gameType;
        this.winnerTeam = winnerTeam;
        this.gameTime = gameTime;
        this.additionalTime = additionalTime;
        this.gameStatus = gameStatus;
        this.moveHistoryFirst = moveHistoryFirst;
        this.moveHistorySecond = moveHistorySecond;
    }

    public String getMoveHistoryFirst() {
        return moveHistoryFirst;
    }

    public void setMoveHistoryFirst(String moveHistoryFirst) {
        this.moveHistoryFirst = moveHistoryFirst;
    }

    public String getMoveHistorySecond() {
        return moveHistorySecond;
    }

    public void setMoveHistorySecond(String moveHistorySecond) {
        this.moveHistorySecond = moveHistorySecond;
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public WinnerTeam getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(WinnerTeam winnerTeam) {
        this.winnerTeam = winnerTeam;
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

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
}
