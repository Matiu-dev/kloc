package com.test.manytomany.model.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.manytomany.model.PlayerBoard.PlayerBoard;
import com.test.manytomany.model.board.Board;
import com.test.manytomany.model.chat.Chat;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.Entity;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Player")
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String password;

    private PlayerStatus playerStatus;

    @Enumerated(EnumType.STRING)
    private PlayerRole playerRole;

    private int wins;

    private int loses;

    private String icon;

    @JsonIgnore
    @OneToMany(mappedBy = "player",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            orphanRemoval = true)
    private Set<PlayerBoard> boards = new HashSet<>();

    @ManyToMany(mappedBy = "players",
            cascade = {CascadeType.MERGE})
    Set<Chat> chats = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }

    public PlayerRole getPlayerRole() {
        return playerRole;
    }

    public void setPlayerRole(PlayerRole playerRole) {
        this.playerRole = playerRole;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Set<PlayerBoard> getBoards() {
        return boards;
    }

    public void setBoards(Set<PlayerBoard> boards) {
        this.boards = boards;
    }

    public Set<Chat> getChats() {
        return chats;
    }

    public void setChats(Set<Chat> chats) {
        this.chats = chats;
    }
}
