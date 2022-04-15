package com.test.manytomany.model.connect;

import java.util.UUID;

public class NotificateConnectResponse {

    private String login;
    private UUID gameId;
    private String type;
    private UUID boardId;
    private UUID boardIdAdditional;

    //pozostali gracze
    private String loginBoardFirstWhite;
    private String loginBoardFirstBlack;
    private String loginBoardSecondWhite;
    private String loginBoardSecondBlack;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLoginBoardFirstWhite() {
        return loginBoardFirstWhite;
    }

    public void setLoginBoardFirstWhite(String loginBoardFirstWhite) {
        this.loginBoardFirstWhite = loginBoardFirstWhite;
    }

    public String getLoginBoardFirstBlack() {
        return loginBoardFirstBlack;
    }

    public void setLoginBoardFirstBlack(String loginBoardFirstBlack) {
        this.loginBoardFirstBlack = loginBoardFirstBlack;
    }

    public String getLoginBoardSecondWhite() {
        return loginBoardSecondWhite;
    }

    public void setLoginBoardSecondWhite(String loginBoardSecondWhite) {
        this.loginBoardSecondWhite = loginBoardSecondWhite;
    }

    public String getLoginBoardSecondBlack() {
        return loginBoardSecondBlack;
    }

    public void setLoginBoardSecondBlack(String loginBoardSecondBlack) {
        this.loginBoardSecondBlack = loginBoardSecondBlack;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public void setBoardId(UUID boardId) {
        this.boardId = boardId;
    }

    public void setBoardIdAdditional(UUID boardIdAdditional) {
        this.boardIdAdditional = boardIdAdditional;
    }

    public UUID getGameId() {
        return gameId;
    }

    public UUID getBoardId() {
        return boardId;
    }

    public UUID getBoardIdAdditional() {
        return boardIdAdditional;
    }
}
