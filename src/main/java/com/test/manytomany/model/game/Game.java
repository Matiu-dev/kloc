package com.test.manytomany.model.game;

import com.test.manytomany.model.PlayerBoard.Team;
import com.test.manytomany.model.board.Board;

import javax.persistence.*;
import java.sql.Time;
import java.util.Set;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "game")
    private Set<Board> boards;

    @Enumerated(EnumType.STRING)
    private WinnerTeam winnerTeam;

    private String gameTime;
    private String additionalTime;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Board> getBoards() {
        return boards;
    }

    public void setBoards(Set<Board> boards) {
        this.boards = boards;
    }

    public WinnerTeam getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(WinnerTeam winnerTeam) {
        this.winnerTeam = winnerTeam;
    }
}
