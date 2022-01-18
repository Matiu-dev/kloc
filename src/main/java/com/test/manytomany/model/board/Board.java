package com.test.manytomany.model.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.manytomany.model.game.Game;
import com.test.manytomany.model.player.Player;

import javax.persistence.*;
import java.util.*;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String test;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "player_board",
            joinColumns = @JoinColumn(name = "board_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    @JsonIgnore
    private Set<Player> players = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public void addPlayer(Player player) {
        this.players.add(player);
        player.getBoards().add(this);
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
        player.getBoards().remove(this);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
