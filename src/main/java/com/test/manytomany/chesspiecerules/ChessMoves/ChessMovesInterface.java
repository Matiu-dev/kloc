package com.test.manytomany.chesspiecerules.ChessMoves;

import com.test.manytomany.model.GamePlay;

public interface ChessMovesInterface {

    GamePlay checkMoveWhite(GamePlay gamePlay);

    GamePlay checkMoveBlack(GamePlay gamePlay);
}
