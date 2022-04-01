package com.test.manytomany.model.connect;

import com.test.manytomany.model.player.Player;

public class CreateGameRequest {

    private Long id;
    private String gameTime;
    private String additionalTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
