package com.test.manytomany.chesspiecerules;

import com.test.manytomany.model.GamePlay;

public class ReserveFigure {

    private GamePlay gamePlay;

    public ReserveFigure(GamePlay gamePlay) {
        this.gamePlay = gamePlay;
    }

    public GamePlay check() {
        return gamePlay;
    }
}
