package com.test.manytomany.model.chat;

import com.test.manytomany.model.PlayerBoard.Color;
import com.test.manytomany.model.PlayerBoard.PlayerBoard;
import com.test.manytomany.model.PlayerBoard.Team;
import com.test.manytomany.model.game.Game;
import com.test.manytomany.model.player.Player;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Chat")
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "player_chat",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    private Set<Player> players = new HashSet<>();;



    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
