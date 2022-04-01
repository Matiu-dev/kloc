package com.test.manytomany.model.connect;

public class NotificateConnectResponse {

    private String login;
    private String gameId;
    private String type;
    private Long boardId;
    private Long boardIdAdditional;

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

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
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

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Long getBoardIdAdditional() {
        return boardIdAdditional;
    }

    public void setBoardIdAdditional(Long boardIdAdditional) {
        this.boardIdAdditional = boardIdAdditional;
    }
}
