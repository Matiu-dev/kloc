package com.test.manytomany.model.connect;

public class NotificateConnectRequest {

    private Long id;
    private String type;
    private String gameId;
    private Long boardId;
    private Long boardIdAdditional;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
