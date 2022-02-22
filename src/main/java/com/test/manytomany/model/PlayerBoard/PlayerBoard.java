package com.test.manytomany.model.PlayerBoard;


import com.test.manytomany.model.board.Board;
import com.test.manytomany.model.player.Player;

import javax.persistence.*;

@Entity(name = "PlayerBoard")
@Table(name = "player_board")
public class PlayerBoard {

    @EmbeddedId
    private PlayerBoardId id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @MapsId("playerId")
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @MapsId("boardId")
    private Board board;

    @Enumerated(EnumType.STRING)
    private Color color;

    @Enumerated(EnumType.STRING)
    private Team team;

    public PlayerBoard() {

    }

    public PlayerBoard(Player player, Board board) {
        this.player = player;
        this.board = board;
        this.id = new PlayerBoardId(player.getId(), board.getId());
    }

    public PlayerBoardId getId() {
        return id;
    }

    public void setId(PlayerBoardId id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
