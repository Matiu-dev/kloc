package com.test.manytomany.model.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.manytomany.model.PlayerBoard.Color;
import com.test.manytomany.model.PlayerBoard.PlayerBoard;
import com.test.manytomany.model.PlayerBoard.Team;
import com.test.manytomany.model.game.Game;
import com.test.manytomany.model.player.Player;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;


@Entity(name = "Board")
@Table(name = "board")
public class Board {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;


    @JsonIgnore
    @OneToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "board",
            orphanRemoval = true)
    private Set<PlayerBoard> players = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    private String moveHistory;

    public void addPlayer(Player player, Color color, Team team) {
        PlayerBoard playerBoard = new PlayerBoard(player, this);
        playerBoard.setColor(color);
        playerBoard.setTeam(team);
        this.players.add(playerBoard);

    }

    public void removePlayer(Player player) {
        this.players.remove(player);
        player.getBoards().remove(this);
    }

    public String getMoveHistory() {
        return moveHistory;
    }

    public void setMoveHistory(String moveHistory) {
        this.moveHistory = moveHistory;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<PlayerBoard> getPlayers() {
        return players;
    }

    public void setPlayers(Set<PlayerBoard> players) {
        this.players = players;
    }


}
