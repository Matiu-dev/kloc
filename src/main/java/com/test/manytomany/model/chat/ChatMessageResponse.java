package com.test.manytomany.model.chat;

import com.test.manytomany.model.PlayerBoard.Team;

public class ChatMessageResponse {

    private MessageStatus messageStatus;
    private MessageCommand messageCommand;
    private String message;
    private String type;
    private Team team;
    private String whisperLogin;
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getWhisperLogin() {
        return whisperLogin;
    }

    public void setWhisperLogin(String whisperLogin) {
        this.whisperLogin = whisperLogin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public MessageStatus getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }

    public MessageCommand getMessageCommand() {
        return messageCommand;
    }

    public void setMessageCommand(MessageCommand messageCommand) {
        this.messageCommand = messageCommand;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
