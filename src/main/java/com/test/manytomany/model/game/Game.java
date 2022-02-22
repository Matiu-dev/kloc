package com.test.manytomany.model.game;

import com.test.manytomany.model.PlayerBoard.Team;
import com.test.manytomany.model.board.Board;

import javax.persistence.*;
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
