package com.test.manytomany.model;

import org.springframework.security.core.parameters.P;

public enum Pieces implements PiecesInterface{
    WHITEPAWN("♙"),
    BLACKPAWN("♟"),

    WHITEROOK("♖"),
    BLACKROOK("♜"),

    WHITEBISHOP("♗"),
    BLACKBISHOP("♝"),

    WHITEKING("♔"),
    BLACKKING("♚"),

    WHITEKNIGHT("♘"),
    BLACKKNIGHT("♞"),

    WHITEQUEEN("♕"),
    BLACKQUEEN("♛"),

    EMPTY("");


    private final String piece;

    Pieces(String piece){
        this.piece = piece;
    }

    @Override
    public String getPiece() {
        return piece;
    }
}
